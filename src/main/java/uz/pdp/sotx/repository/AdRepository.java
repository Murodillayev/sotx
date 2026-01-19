package uz.pdp.sotx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.sotx.model.dto.AdDto;
import uz.pdp.sotx.model.dto.AdInterfaceProjection;
import uz.pdp.sotx.model.dto.IdNameDto;
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


    @Query(value = """
            select new uz.pdp.sotx.model.dto.AdDto(a.id,a.title,a.price,a.currency,a.description,a.category) from Ad a 
              where not a.deleted 
                    and (:category is null or a.category = :category)
                    and a.title ilike concat('%',:search,'%') 
            """)
    Page<AdDto> findAllByProjection(String search, Category category, Pageable pageable);

    @Query(value = """
            select a from Ad a 
              where not a.deleted 
                    and (:category is null or a.category = :category)
                    and a.title ilike concat('%',:search,'%') 
            """)
    Page<AdInterfaceProjection> findAllByInterfaceProjection(String search, Category category, Pageable pageable);


    @Query(value = """
            select a.* from ad a 
              where not a.deleted 
                    and (:category is null or a.category = :category)
                    and a.title ilike concat('%',:search,'%') 
            """, nativeQuery = true)
    Page<AdInterfaceProjection> findAllByInterfaceProjectionNative(String search, String category, Pageable pageable);

    @Query("""
            select new uz.pdp.sotx.model.dto.IdNameDto(a.id, a.title) 
            from Ad a where not a.deleted
            """)
    List<IdNameDto> findAllForSelect();
}


// product(id,name, desc,price, createdAt, status, img, deleted, userId ...)
// prdoctDto(id,name,desc,price) -> product page
// IdNameDto(id,name)  -> select input


// contract
// contractCreateDto(prductId, sellingPrice, sellAt, currency, totalmonth)