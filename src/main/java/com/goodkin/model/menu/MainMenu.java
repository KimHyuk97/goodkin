package com.goodkin.model.menu;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MainMenu {
    private Long mainMenuNo;
    private Long menuNo;
    private Integer sort;

    private Menu menu;        
}
