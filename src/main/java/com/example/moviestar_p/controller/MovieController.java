package com.example.moviestar_p.controller;

import com.example.moviestar.dto.MovieDTO;
import com.example.moviestar.dto.PageRequestDTO;
import com.example.moviestar.dto.PageResponseDTO;
import com.example.moviestar.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @Value("${upload.path}")
    private String uploadPath;

    // 생성
    @GetMapping("/register")
    public void registerGet() {

    }

    @PostMapping("/register")
    public String registerPost(@Valid MovieDTO movieDTO,
                               BindingResult bindingResult,
                               @RequestParam("image") MultipartFile file,
                               RedirectAttributes redirectAttributes) {
        // BindingResult에 결과값이 저장
        log.info("movie POST register...");

        if (bindingResult.hasErrors()) {
            log.info("has errors...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/movie/register";
        }

        if (!file.isEmpty()) {
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadPath, filename);
            try {
                // 파일 저장 디렉토리가 존재하지 않으면 생성
                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }
                file.transferTo(saveFile);
                movieDTO.setImagePath(filename);
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("errors", "File upload failed.");
                return "redirect:/movie/register";
            }
        }

        log.info(movieDTO);
        Long no = movieService.register(movieDTO);
        redirectAttributes.addFlashAttribute("result", no);
        return "redirect:/movie/list";
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            File file = new File(uploadPath, filename);
            Resource resource = new UrlResource(file.toURI());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 목록
    @Operation(summary = "movie/list")
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<MovieDTO> responseDTO = movieService.list(pageRequestDTO);

        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    // 읽기, 수정
    @Operation(summary = "movie/read, /modify")
    @GetMapping({"/read", "/modify"})
    public void read(Long no, PageRequestDTO pageRequestDTO, Model model) {
        MovieDTO movieDTO = movieService.readOne(no);
        log.info(movieDTO);
        model.addAttribute("dto", movieDTO);
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid MovieDTO movieDTO,
                         @RequestParam("image") MultipartFile imageFile,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        log.info("modify..." + movieDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors...");

            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("no", movieDTO.getNo());
            return "redirect:/movie/modify?" + link;
        }

        if (!imageFile.isEmpty()) {
            // 새 이미지를 업로드하고, 해당 경로를 저장
            String imagePath = saveImage(imageFile); // 이미지 경로 지정
            movieDTO.setImagePath(imagePath);
        }

        movieService.modify(movieDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("no", movieDTO.getNo());
        return "redirect:/movie/read";
    }

    private String saveImage(MultipartFile imageFile) {
        try {
            // 이미지 저장할 디렉토리 경로 설정
            String uploadDir = "C:/uploads";

            // 이미지 파일명 생성
            String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();

            // 이미지 저장할 파일 객체 생성
            File saveFile = new File(uploadDir, filename);

            // 이미지 파일을 저장
            imageFile.transferTo(saveFile);

            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 삭제
    @PostMapping("/remove")
    public String remove(Long no, RedirectAttributes redirectAttributes) {
        log.info("remove...");
        log.info(no);
        movieService.remove(no);
        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/movie/list";
    }
}

