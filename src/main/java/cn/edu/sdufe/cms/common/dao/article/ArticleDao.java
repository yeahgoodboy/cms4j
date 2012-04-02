package cn.edu.sdufe.cms.common.dao.article;

import cn.edu.sdufe.cms.common.entity.article.Article;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文章Dao
 * User: baitao.jibt (dreambt@gmail.com)
 * Date: 12-3-20
 * Time: 下午18:34
 */
@Component
public class ArticleDao extends SqlSessionDaoSupport {

    /**
     * 获取所有文章
     *
     * @param rowBounds
     * @return
     */
    @Cacheable(value = "allArticle")
    public List<Article> getAll(Map<String, Object> parameters, RowBounds rowBounds) {
        return getSqlSession().selectList("CMS.getAllArticle", parameters, rowBounds);
    }

    /**
     * 获取分类id的文章
     *
     * @param categoryId
     * @param rowBounds
     * @return
     */
    @Cacheable(value = "articleList")
    public List<Article> getListByCategoryId(Long categoryId, RowBounds rowBounds) {
        return getSqlSession().selectList("CMS.getArticleListByCategoryId", categoryId, rowBounds);
    }

    /**
     * 获取分类id的文章
     *
     * @param categoryId
     * @param rowBounds
     * @return
     */
    @Cacheable(value = "articleDigest")
    public List<Article> getDigestByCategoryId(Long categoryId, RowBounds rowBounds) {
        return getSqlSession().selectList("CMS.getArticleDigestByCategoryId", categoryId, rowBounds);
    }

    /**
     * 获取编号为id的文章
     *
     * @param id
     * @return
     */
    @Cacheable(value = "article")
    public Article get(Long id) {
        return getSqlSession().selectOne("CMS.getArticle", id);
    }

    /**
     * 获取文章数量
     *
     * @return
     */
    @Cacheable(value = "article_num")
    public Long count(Long id) {
        return getSqlSession().selectOne("CMS.getArticleCount", id);
    }

    /**
     * 获取标记为删除的文章id
     *
     * @return
     */
    public List<Long> getDeletedId() {
        return getSqlSession().selectList("CMS.getDeletedArticleId");
    }

    /**
     * 更新文章
     *
     * @param article
     * @return
     */
    @Deprecated
    public void update(Article article) {
        getSqlSession().update("CMS.updateArticle", article);
    }

    /**
     * 搜索文章
     *
     * @param parameters
     * @return
     */
    public List<Article> search(Map<String, Object> parameters) {
        return getSqlSession().selectList("CMS.searchArticle", parameters);
    }

    /**
     * 获得本月的所有文章
     *
     * @return
     */
    @Cacheable(value = "monthArticle")
    public List<Article> getMonthArticle(Date month) {
        return getSqlSession().selectList("CMS.getMonthArticle", month);
    }

}