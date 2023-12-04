package com.senai.Mobili.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@Service
public class FileUploadService {
    private static final Path diretorioImg = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img");
    public static String fazerUpload(MultipartFile imagem) throws IOException {
        if (imagem.isEmpty()){
            System.out.println("imagem vazia");
            return null;
        }
        String [] nomeArquivoArray= imagem.getOriginalFilename().split("\\.");
        String novoNome = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHmss"));
        String extencaoArquivo = nomeArquivoArray [nomeArquivoArray.length -1];

        String nomeImg =novoNome + "." + extencaoArquivo;

        File imagemCriada = new File (diretorioImg + "\\" + nomeImg);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imagemCriada));
        stream.write(imagem.getBytes());
        stream.close();
        return  nomeImg;

    }

}
