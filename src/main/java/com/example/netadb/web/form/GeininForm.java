package com.example.netadb.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GeininForm {
    private String g_id;
    @NotBlank
    private String combi_name;
    private String debut;
    private String description;
    private String office;
}
