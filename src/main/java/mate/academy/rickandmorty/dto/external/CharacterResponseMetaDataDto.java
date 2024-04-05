package mate.academy.rickandmorty.dto.external;

import lombok.Data;

@Data
public class CharacterResponseMetaDataDto {
    private String count;
    private String pages;
    private String next;
    private String prev;
}
