package com.inlog.repository;

import com.inlog.domain.Post;
import com.inlog.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

	List<Post> getList(PostSearch postSearch);
}
