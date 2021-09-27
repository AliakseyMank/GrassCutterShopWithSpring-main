package com.alexey.GrassCutterShopWithSpring.repo;

import com.alexey.GrassCutterShopWithSpring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long> {

}
