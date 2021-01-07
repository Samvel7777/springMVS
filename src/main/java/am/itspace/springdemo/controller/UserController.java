package am.itspace.springdemo.controller;

import am.itspace.springdemo.model.User;
import am.itspace.springdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Value("${file.upload.dir}")
    private String uploadDir;

    private final UserService userService;

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, @RequestParam("image") MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File(uploadDir, name);
        file.transferTo(image);
        user.setProfilePic(name);
        userService.save(user);
        return "redirect:/?msg=User was added";
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteById(id);
        String msg = "User was removed";
        return "redirect:/?msg=" + msg;
    }
}
