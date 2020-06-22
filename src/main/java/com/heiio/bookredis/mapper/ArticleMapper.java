@Mapper
public interface ArticleMapper {
    Article select(
             @Param("id")
                    long id);
    void update(Article article);
}