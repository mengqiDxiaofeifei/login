package com.mengqid.utils.upload;

import com.mengqid.entity.upload.Fileim;
import com.mengqid.entity.upload.UserFile;
import org.springframework.stereotype.Component;

@Component
public class VoToBean {
    public  UserFile fileimToUserFile(Fileim fim, String uid, String currentpath) {
        UserFile uf = new UserFile();
        uf.setFilesize(fim.getFilesize());
        uf.setIconSign(fim.getIconsign());
        uf.setMtime(fim.getMtime());
        uf.setUid(uid);
        uf.setVirtualpath(currentpath + fim.getFilename());
        if (fim.getIconsign().trim() != "#icon-folder") {
            uf.setRealpath("H:/fileUploadRepository/" + fim.getFilename());
        }
        return uf;
    }
}
