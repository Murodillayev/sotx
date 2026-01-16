package uz.pdp.sotx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.sotx.model.entity.Ad;

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


//    @Query(value = """
//            from Ad a
//              where not a.deleted
//                    and (:category is null or a.category = :category)
//                    and a.title ilike concat('%',:search,'%')
//            """)

    @Query(value = """
            select a.* from ad a 
              where not a.deleted 
                    and (:category is null or a.category = :category)
                    and a.title ilike concat('%',:search,'%') 
            """, nativeQuery = true)
    Page<Ad> findAllByCriteria(String search, String category, Pageable pageable);

}
