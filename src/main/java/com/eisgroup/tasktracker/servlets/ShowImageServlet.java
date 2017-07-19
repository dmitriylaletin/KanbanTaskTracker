package com.eisgroup.tasktracker.servlets;

import com.eisgroup.tasktracker.service.AuthManager;
import com.eisgroup.tasktracker.service.ImageService;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:37
 */
public class ShowImageServlet extends HttpServlet {
    private static final String INDEX_PAGE = "/index.xhtml";
    private static final int IMAGE_PREVIEW_SIZE = 400;
    private static final String IMAGE_ID_PARAMETER = "id";
    private static final String IMAGE_PREVIEW = "preview";

    @Autowired
    AuthManager authManager;

    @Autowired
    ImageService imageService;

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { doGet(request, response); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int imageId = Integer.valueOf(request.getParameter(IMAGE_ID_PARAMETER));
            byte[] image = imageService.getTaskImage(authManager.getCurrentUser().getId(), imageId);
            if (request.getParameter(IMAGE_PREVIEW) != null) {
                image = makePreview(image);
            }
            response.setContentType("image/jpeg");
            OutputStream out = response.getOutputStream();
            response.setContentLength(image.length);
            out.write(image);
            out.close();
        } catch (Exception e) {
            response.reset();
            response.sendError(404);
        }
    }

    private byte[] makePreview(byte[] image) throws IOException {
        BufferedImage thumb = ImageIO.read(new ByteArrayInputStream(image));
        if ((thumb.getWidth() > IMAGE_PREVIEW_SIZE) || (thumb.getHeight() > IMAGE_PREVIEW_SIZE)) {
            thumb = Scalr.resize(thumb, IMAGE_PREVIEW_SIZE);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(thumb, "jpg", out);
            out.flush();
            image = out.toByteArray();
        }
        return image;
    }
}
