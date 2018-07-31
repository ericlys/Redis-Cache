package view;

import com.test.beans.Produto;
import dao.ProductDAO;

public class App {
    public static void main(String[] args) {
        Produto p1 = new Produto (1, "Camisa", 25);
        ProductDAO dao = new ProductDAO ();

        System.out.println ( dao.registerProductPG (p1));

        System.out.println (dao.BuscarProduto (1).toString ());

    }
}
