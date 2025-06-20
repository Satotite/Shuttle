package com.simplecity.amp_library.model;

import android.content.Context;
import com.simplecity.amp_library.interfaces.FileType;
import com.simplecity.amp_library.utils.FileHelper;
import com.simplecity.amp_library.utils.StringUtils;

public class FileObject extends BaseFileObject {

    public String extension;
        @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FileObject)) return false;
        FileObject other = (FileObject) obj;
        return extension != null ? extension.equals(other.extension) : other.extension == null;
    }

    @Override
    public int hashCode() {
        return extension != null ? extension.hashCode() : 0;
    }

    public TagInfo tagInfo;

    private long duration = 0;

    public FileObject() {
        this.fileType = FileType.FILE;
    }

    public String getTimeString(Context context) {
        if (duration == 0) {
            duration = FileHelper.getDuration(context, this);
        }
        return StringUtils.makeTimeString(context, duration / 1000);
    }

    @Override
    public String toString() {
        return "FileObject{" +
                "extension='" + extension + '\'' +
                ", size='" + size + '\'' +
                "} " + super.toString();
    }
}
