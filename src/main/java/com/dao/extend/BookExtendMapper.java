package com.dao.extend;

import com.bean.Book;
import com.bean.extend.BookExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookExtendMapper {
    List<Book> findBookLike(@Param("status") String status,
                            @Param("name") String name,
                            @Param("categoryId") Integer categoryId,
                            @Param("orderField") String orderField);

    List<Book> findRelationBook(Integer id);

    List<BookExtend> findByUserIdAndStatus(@Param("userId") Integer userId,
                                           @Param("status") String status);
}
