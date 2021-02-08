package com.example.netadb.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NetaForm {
    private String n_id;
    private String g_id;
    @NotBlank
    private String neta_name;
    private String description;
    private String link;
}