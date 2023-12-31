package com.inlog.controller;

import com.inlog.request.PostCreate;
import com.inlog.request.PostEdit;
import com.inlog.request.PostSearch;
import com.inlog.response.PostResponse;
import com.inlog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

	private final PostService postService;

	@PostMapping
	public void post(@RequestBody @Valid PostCreate request) {
		request.validate();
		postService.write(request);
	}

	@GetMapping("/{postId}")
	public PostResponse get(@PathVariable Long postId) {
		return postService.get(postId);
	}

	@GetMapping
	public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
		return postService.getList(postSearch);
	}

	@PatchMapping("/{postId}")
	public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
		postService.edit(postId, request);
	}

	@DeleteMapping("/{postId}")
	public void delete(@PathVariable Long postId) {
		postService.delete(postId);
	}
}