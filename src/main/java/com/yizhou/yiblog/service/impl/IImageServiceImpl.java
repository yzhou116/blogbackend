package com.yizhou.yiblog.service.impl;

import com.yizhou.yiblog.dao.ImageDAO;
import com.yizhou.yiblog.pojo.Category;
import com.yizhou.yiblog.pojo.Image;
import com.yizhou.yiblog.pojo.User;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.IImageService;
import com.yizhou.yiblog.service.IUserService;
import com.yizhou.yiblog.util.Constrants;
import com.yizhou.yiblog.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class IImageServiceImpl implements IImageService {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM-dd");

    @Value("${yi.blog.image.save-path}")
    private String ImagePath;
    @Value("${yi.blog.image.max-size}")
    public long maxSize;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private IUserService iUserService;
    @Autowired
    private ImageDAO imageDAO;

    @Override
    public ResponseResult uploadFile(MultipartFile file) {

        if (file == null) {
            return ResponseResult.FAIL("File is null");
        }
        //check the type of file,only support image: png,jpg,gif
        String contentType = file.getContentType();
        if (contentType == null) {
            return ResponseResult.FAIL("File is wrong");
        }

        String originalFilename = file.getOriginalFilename();
        String type = getType(contentType);
        if (type == null) {
            return ResponseResult.FAIL("Can't support this type of file");
        }
        //save

        long size = file.getSize();
        if (size > maxSize) {
            return ResponseResult.FAIL("Image too large");
        }
        long currentTimeMillis = System.currentTimeMillis();
        String currentDate = simpleDateFormat.format(currentTimeMillis);
        String dayPath = ImagePath + File.separator + currentDate;
        File dayPathFile = new File(dayPath);
        if (!dayPathFile.exists()) {
            dayPathFile.mkdirs();

        }
        String targetName = String.valueOf(snowflakeIdWorker.nextId());

        String targetPath = dayPath + File.separator + type
                + File.separator + targetName + "." + type;
        File targetFile = new File(targetPath);
        if (!targetFile.getParentFile().exists()) {
            targetFile.mkdirs();
        }


        try {
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            file.transferTo(targetFile);
            Map<String, String> result = new HashMap<>();

            String resultPath = currentTimeMillis + "_" + targetName + "." + type;
            result.put("id", resultPath);
            result.put("name", originalFilename);
            Image image = new Image();
            image.setContentType(contentType);
            image.setId(targetName);
            image.setCreateTime(new Date());
            image.setUpdateTime(new Date());
            image.setPath(targetFile.getPath());
            image.setName(originalFilename);
            image.setUrl(resultPath);
            image.setState("1");
            User user = iUserService.checkUser();
            image.setUserId(user.getId());
            imageDAO.save(image);
            return ResponseResult.SUCCESS("Success").setData(result);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return ResponseResult.FAIL("Upload fail");
    }

    private String getType(String contentType) {
        String type = null;
        if ("image/png".equals(contentType)) {
            type = "png";

        } else if ("image/jpg".equals(contentType)) {
            type = "jpg";
        } else if ("image/gif".equals(contentType)) {
            type = "gif";
        } else if ("image/jpeg".equals(contentType)) {
            type = "jpeg";
        }
        return type;
    }

    @Override
    public ResponseResult viewImage(HttpServletResponse response, String imageId) throws IOException {

        String[] paths = imageId.split("_");
        String timeValue = paths[0];
        String format = simpleDateFormat.format(Long.parseLong(timeValue));
        String name = paths[1];
        String type;
        if (name.endsWith("jpeg")) {
            type = name.substring(name.length() - 4);
        } else {
            type = name.substring(name.length() - 3);
        }


        String targetPath = ImagePath + File.separator + format +
                File.separator + type +
                File.separator + name;

        File file = new File(targetPath);
        ServletOutputStream writer = null;
        FileInputStream fos = null;
        try {
            response.setContentType("image/png");
            writer = response.getOutputStream();
            fos = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = fos.read(buffer)) != -1) {
                writer.write(buffer, 0, length);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (writer != null) {
                writer.close();
            }
            if (fos != null) {
                fos.close();
            }

        }
        return ResponseResult.SUCCESS("Get File");
    }

    @Override
    public ResponseResult ListImages(int page, int size) {
        if (page < Constrants.Page.DEFAULT_PAGE) {
            page = Constrants.Page.DEFAULT_PAGE;
        }
        if (size < Constrants.Page.MIN_SIZE) {
            size = Constrants.Page.MIN_SIZE;
        }
        User user = iUserService.checkUser();
        String userId = user.getId();

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createTime").descending());
        Page<Image> categoryList = imageDAO.findAll(new Specification<Image>() {
            @Override
            public Predicate toPredicate(Root<Image> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate userId1 = criteriaBuilder.equal(root.get("userId").as(String.class), userId);
                Predicate state = criteriaBuilder.equal(root.get("state").as(String.class), "1");
                return criteriaBuilder.and(userId1, state);
            }
        }, pageable);


        return ResponseResult.SUCCESS("Success").setData(categoryList);
    }

    @Override
    public ResponseResult deleteById(String imageId) {
        int result = imageDAO.deleteImageByUpdateState(imageId);

        return result > 0 ? ResponseResult.SUCCESS("Success") : ResponseResult.FAIL("Fail to delete");
    }
}
