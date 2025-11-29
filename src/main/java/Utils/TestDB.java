package Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestDB {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
            EntityManager em = emf.createEntityManager();
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
            em.close();
            emf.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kết nối thất bại!");
        }
    }
}