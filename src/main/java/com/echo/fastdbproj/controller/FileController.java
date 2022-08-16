package com.echo.fastdbproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("file")
public class FileController {
    private String UPLOADED_FOLDER;
    private static final Set<String> typeSet = Set.of("customer", "driver", "car", "admin");

    @Autowired
    @Qualifier("fileBasePath")
    public void setUPLOADED_FOLDER(String UPLOADED_FOLDER) {
        this.UPLOADED_FOLDER = UPLOADED_FOLDER;
    }

    boolean isEmptyDirectory(Path dir) throws IOException {
        try (var entries = Files.list(dir)) {
            return entries.findAny().isEmpty();
        }
    }

    @RequestMapping("get-pic")
    public Resource getPic() {

        return null;
    }




    @RequestMapping("upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes,
                             String userId,
                             Optional<String> type) throws FileNotFoundException {
        String _type = type.orElse("customer");
        //        var loggedIn = StpUtil.isLogin();
        if ( !typeSet.contains(_type)) {
            System.err.println("User ID:" + userId +" Not log in");
            return "Not available. Access denied";
        }
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere

            var dirPath = Paths.get(UPLOADED_FOLDER + _type + "\\" + userId + "\\");
            Files.createDirectories(dirPath);
            if (!isEmptyDirectory(dirPath)) {
                FileUtils.deleteDirectory(new File(String.valueOf(dirPath)));
            }
            Files.createDirectories(dirPath);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + _type + "\\" + userId + "\\" + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }
}
