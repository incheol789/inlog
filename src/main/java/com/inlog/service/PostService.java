package com.inlog.service;

import com.inlog.domain.Post;
import com.inlog.exception.PostNotFound;
import com.inlog.repository.PostRepository;
import com.inlog.request.PostCreate;
import com.inlog.request.PostEdit;
import com.inlog.request.PostSearch;
import com.inlog.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;

	public void write(PostCreate postCreate) {
		Post post = Post.builder()
				.title(postCreate.getTitle())
				.content(postCreate.getContent())
				.build();

		postRepository.save(post);
	}

	public PostResponse get(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(PostNotFound::new);

		return PostResponse.builder()
				.id(post.getId())
				.title(post.getTitle())
				.content(post.getContent())
				.build();
	}

	public List<PostResponse> getList(PostSearch postSearch) {
		return postRepository.getList(postSearch).stream()
				.map(PostResponse::new)
				.collect(Collectors.toList());
	}

	@Transactional
	public void edit(Long id, PostEdit postEdit) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFound::new);

//		PostEditor.PostEditorBuilder editorBuilder = post.toEditor();
//
//		PostEditor postEditor = editorBuilder
//				.title(postEdit.getTitle())
//				.content(post.getContent())
//				.build();

		post.change(postEdit.getTitle(), postEdit.getContent());

	}

	@Transactional
	public void delete(Long id) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFound::new);

		postRepository.delete(post);

	}
}
