package com.booktree;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by han on 2017-03-23.
 */
@Repository
public interface QuickHelpMapper {
	String selectPriorityHelpUrl(@Param("urlList") List<String> urlList);

	String selectPrioritySearchUrl(@Param("urlList") List<String> urlList);
}
