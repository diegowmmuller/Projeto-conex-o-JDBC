package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args) throws ParseException {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        // Teste de CRUD para Seller
        System.out.println("==== TEST find by id ===");
        Seller seller = sellerDao.findById(4);        
        System.out.println(seller);
        System.out.println();
        
        System.out.println("==== TEST find by department ===");
        Department dep = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(dep);
        for(Seller sel : list) {
            System.out.println(sel);
        }
        System.out.println();
        
        System.out.println("=== TEST find all ====");
        List<Seller> listAll = sellerDao.findAll();
        for(Seller sel : listAll) {
            System.out.println(sel);
        }
        System.out.println();
        
        
        System.out.println("==== Test Insert Seller ====");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = sdf.parse("23/08/1996");
        
        Department depForSeller = new Department(2, "Electronics");        
        Seller sel = new Seller(null, "Diego Muller", "diego@gmail.com", birthDate, 5000.0, depForSeller);
        
        sellerDao.insert(sel);
        System.out.println("Novo vendedor inserido com ID: " + sel.getId());
        System.out.println();
        
        
        System.out.println("==== Test Update Seller ====");
        Seller sellerToUpdate = sellerDao.findById(1);
        sellerToUpdate.setName("Martha Waine");
        sellerToUpdate.setEmail("martha@gmail.com");
        sellerDao.update(sellerToUpdate);
        System.out.println("Vendedor atualizado: " + sellerToUpdate);
        System.out.println();


        // Teste de CRUD para Department
        System.out.println("==== TEST find by id ===");
        Department department = departmentDao.findById(1);        
        System.out.println(department);
        System.out.println();
        
        System.out.println("==== TEST find all departments ====");
        List<Department> departments = departmentDao.findAll();
        for (Department depAll : departments) {
            System.out.println(depAll);
        }
        System.out.println();
        
        System.out.println("==== Test Insert Department ====");
        Department newDepartment = new Department(null, "Marketing");
        departmentDao.insert(newDepartment);
        System.out.println("Novo departamento inserido com ID: " + newDepartment.getId());
        System.out.println();

        System.out.println("==== Test Update Department ====");
        Department departmentToUpdate = departmentDao.findById(newDepartment.getId());
        departmentToUpdate.setName("Digital Marketing");
        departmentDao.update(departmentToUpdate);
        System.out.println("Departamento atualizado: " + departmentToUpdate);
        System.out.println();
        
        System.out.println("==== Test Delete Department ====");
        departmentDao.deleteById(newDepartment.getId());
        System.out.println("Departamento excluído com sucesso.");
    }
}