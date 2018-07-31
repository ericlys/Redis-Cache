package dao;

import com.google.gson.Gson;
import com.test.beans.Produto;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {

    public int registerProductPG(Produto produto){
        int rowAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase ();

            String insertQuery = "insert into produto values (?,?,?) ";
            PreparedStatement sta = connection.prepareStatement (insertQuery);
            sta.setInt (1, produto.getCodigo ());
            sta.setString (2,produto.getDescription ());
            sta.setFloat (3,produto.getPrice ());
            rowAffected = sta.executeUpdate ();

        }catch (SQLException ex){
            ex.printStackTrace ();
        }

        return rowAffected;
    }

    private Produto getProductpPG(Integer codigo){
        Produto product = null;
        try {
            Connection connection = DBConnection.getConnectionToDatabase ();

            String sql = " select * from produto where codigo = ?";
            PreparedStatement statement = connection.prepareStatement (sql);
            statement.setInt (1, codigo);

            ResultSet set = statement.executeQuery ();
            while(set.next ()){
                product = new Produto ();
                product.setCodigo ( set.getInt ("codigo"));
                product.setDescription (set.getString ("descricao"));
                product.setPrice (set.getFloat ("preco"));
            }

        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        return product;

    }

    private Produto getProductRD(int codigo){
        Jedis jedis = new Jedis ("localhost", 6379);
        Gson gson = new Gson();
        Produto product = null;

        String produto = jedis.get (Integer.toString (codigo));
        product = gson.fromJson(produto, Produto.class);

        return product;
    }

    private void registerProductRD(Produto produto, int temp){
        Jedis jedis = new Jedis ("localhost", 6379);
        Gson gson = new Gson();

        String json = gson.toJson(produto);
        jedis.setex (Integer.toString (produto.getCodigo ()), temp, json);

    }

    public Produto BuscarProduto(int codigo){
        Produto product = null;
        product = getProductRD (codigo);
        if (product!=null){
            System.out.println ("product in Redis DB" );
            return product;
        }else{
            product = getProductpPG (codigo);
            if (product!=null){
                registerProductRD (product, 1800);
                System.out.println ("product in PG DB" );
                return product;
            }else{
                System.out.println ("produto não cadastrado");
            }

        }
        return product;
    }


}
