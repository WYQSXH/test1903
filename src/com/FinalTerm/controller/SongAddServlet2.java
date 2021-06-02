package com.FinalTerm.controller;

import com.FinalTerm.entity.Songs2;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//https://blog.csdn.net/qq_45166661/article/details/108227776?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-1&spm=1001.2101.3001.4242
public class SongAddServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //https://blog.csdn.net/weixin_44554896/article/details/107883063
        //判断请求头中是否还有 enctype="multipart/form-data"
        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter out = response.getWriter();
            out.println("Error: 表单必须包含 enctype=multipart/form-data");
            out.flush();
            return;
        } else {
            int sizeThreshold = 1024 * 1024 * 5;//设置内存临界值 5M
            int fileSizeMax = 1024 * 1024 * 5; //设置单个文件的最大大小
            int sizeMax = 1024 * 1024 * 11;//设置请求的最大大小

            DiskFileItemFactory factory = new DiskFileItemFactory();
            //当文件超过设置的值时就写入到临时文件夹，否则就保存在内存
            factory.setSizeThreshold(sizeThreshold);
            //设置DiskFileItemFactory的临时文件夹
            //java.io.tmpdir 代表系统的temp目录
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setHeaderEncoding("utf-8");//设置编码
            //设置单个文件的最大大小
            upload.setFileSizeMax(fileSizeMax);
            //设置请求的最大大小
            upload.setSizeMax(sizeMax);

            //构建上传目录的路径
            String uploadPath = "E:\\upload";
            //如果目录不存在就创建
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            Songs2 song = new Songs2();
            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                for (FileItem fileItem : fileItems) {
                    if (!fileItem.isFormField()) {
                        //文件元素
                        if ("picture".equals(fileItem.getFieldName())) {
                            //获取原来的扩展名
                            String oldName = new File(fileItem.getName()).getName();
                            String extensionName = ".jpg";
                            //获取扩展名称
                            if ((oldName != null) && (oldName.length() > 0)) {
                                int dot = oldName.lastIndexOf('.');
                                if ((dot > -1) && (dot < (oldName.length() - 1))) {
                                    extensionName = oldName.substring(dot);
                                }
                            }
                            //构建文件的名称
                            String fileName = System.currentTimeMillis() + "_" + System.nanoTime() + extensionName;
                            String filePath = uploadPath + File.separator + fileName;
                            //保存文件
                            try {
                                fileItem.write(new File(filePath));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            song.setSongName(fileName);
                        }
                    }
                }
                //调用Service保存数据
//                song.setSongUrl(new Date());
//                boolean bolS=userService.insert(song);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }
}

    /*
    //https://blog.csdn.net/weixin_34235371/article/details/91962287

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String songName = request.getParameter("songName");
        String singer = request.getParameter("singer");
        String songFile = request.getParameter("songFile");
        String songImage = request.getParameter("songImage");
        out.print(songFile);
        out.print(songImage);

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message",
                                "文件上传成功!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        // 跳转到 message.jsp
        getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
    }

    //https://blog.csdn.net/weixin_30437847/article/details/95475024
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        UserService service = new UserServiceImpl();
        // 创建磁盘工厂 缓冲区和磁盘目录
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置文件上传的大小限制
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(1024 * 1024 * 20);// 最大上传20M
        upload.setHeaderEncoding("utf-8");// 设置编码格式
        try {
            String songName = request.getParameter("songName");
            String singer = request.getParameter("singer");
            String songFile = request.getParameter("songFile");
            String songImage = request.getParameter("songImage");
            String name = null;
            // 注册获取前台的值
            String value = null;
            String newName = null;
            ArrayList<Object> list = new ArrayList<Object>();
            @SuppressWarnings("unchecked")
            List<FileItem> files = upload.parseRequest(request);
            for (FileItem fileItem : files) {
                // 判断当前的数据时文件还是普通的表单
                // 是文件
                if (fileItem.isFormField()) {
                    name = fileItem.getFieldName();// 获取属性的名字
                    value = fileItem.getString("utf-8");// 获取属性的值
                    list.add(value);
                } else {
                    // 是表单
                    // 获取文件上传的文件名
                    name = fileItem.getName();

                    // 定义一个新的文件来接收
                    newName = System.currentTimeMillis() + name;// 以当前的总秒数来命名防止图片名称相同而覆盖
                    File file = new File("D:\\image\\" + newName);

                    // 图片的存储路径
                    String headImg = "img/" + newName;
                    list.add(headImg);
                    // 从缓冲区写入磁盘
                    fileItem.write(file);
                    fileItem.delete();
                }

            }

            // 获取到全部的用户列表
            List<User> uList = service.allUser();

            // 将新注册的用户存入数据
            boolean flag = service.register(list);

            if (flag) {
                System.out.println("注册成功");

                // 跳转到登陆界面
                request.getRequestDispatcher("/page/login.jsp").forward(request, response);

            } else {
                System.out.println("注册失败");
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
