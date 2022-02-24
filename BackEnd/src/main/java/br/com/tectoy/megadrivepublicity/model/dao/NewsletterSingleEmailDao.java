package br.com.tectoy.megadrivepublicity.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsletterSingleEmailDao extends NewsletterDao{
    private String email;
}
