package uz.pdp.sotx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.sotx.model.entity.Ad;
import uz.pdp.sotx.model.enums.Category;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, String> {

//    @Query(value = """
//            from Ad a
//              where not a.deleted
//                    and a.category = :category
//                    and a.title ilike concat('%',:search,'%')
//            """)
//    List<Ad> findAllBySearch(String search, Category category);
//
//    @Query(value = """
//            from Ad a
//              where not a.deleted
//                    and a.title ilike concat('%',:search,'%')
//            """)
//    List<Ad> findAllBySearch(String search);


    @Query(value = """
            from Ad a 
              where not a.deleted 
                    and (:category is null or a.category = :category)
                    and a.title ilike concat('%',:search,'%') 
            """)
    List<Ad> findAllByCriteria(String search, Category category);

}
