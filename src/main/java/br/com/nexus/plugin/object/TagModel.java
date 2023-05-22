package br.com.nexus.plugin.object;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TagModel {

    public TagModel(String tag, String hieraquia, String permission) {
        this.tag = tag;
        this.hieraquia = hieraquia;
        this.permission = permission;
    }

    public String tag;
    public String hieraquia;
    public String permission;

}
