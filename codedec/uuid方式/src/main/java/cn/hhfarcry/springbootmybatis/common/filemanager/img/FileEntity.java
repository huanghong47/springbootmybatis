package cn.hhfarcry.springbootmybatis.common.filemanager.img;

import cn.hhfarcry.springbootmybatis.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-21 14:52
 */
@NoArgsConstructor
public class FileEntity extends BaseEntity {

    @ApiModelProperty(value="文件/文件夹名",name="fileName",example="")
    @NotNull(message = "文件/文件夹名不能为空", groups = {FolderAddGroup.class})
    @Setter @Getter
    private String fileName;

    @ApiModelProperty(value="是否文件(0:是;-1不是代表文件夹)",name="id",example="1")
    @NotNull(message = "是否文件不能为空", groups = {FolderAddGroup.class})
    @Setter @Getter
    private Integer isFile;

    @ApiModelProperty(value="父级目录id(若是顶级文件夹则传0)",name="id",example="1")
    @NotNull(message = "父级目录id不能为空", groups = {FolderAddGroup.class})
    @Setter @Getter
    private Integer parentId;

    //以下三个字段属于文件，不属于文件夹
    @ApiModelProperty(value="文件类型(图片/视频/excel/word等)",name="fileType",example="1")
    @Setter @Getter
    private String fileType;

    @ApiModelProperty(value="文件前缀",name="filePrefix",example="1")
    @Setter @Getter
    private String filePrefix;

    @ApiModelProperty(value="文件后缀",name="fileSuffix",example="1")
    @Setter @Getter
    private String fileSuffix;

    @ApiModelProperty(value="原图文件/文件夹地址",name="fileUrl",example="1")
    @Setter @Getter
    private String fileUrl;

    @ApiModelProperty(value="缩略图地址",name="fileUrl",example="1")
    @Setter @Getter
    private String thumbnailUrl;

}
