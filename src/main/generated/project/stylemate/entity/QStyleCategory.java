package project.stylemate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStyleCategory is a Querydsl query type for StyleCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStyleCategory extends EntityPathBase<StyleCategory> {

    private static final long serialVersionUID = 24897297L;

    public static final QStyleCategory styleCategory = new QStyleCategory("styleCategory");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath categoryName = createString("categoryName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDateTime = _super.createDateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDateTime = _super.updateDateTime;

    public QStyleCategory(String variable) {
        super(StyleCategory.class, forVariable(variable));
    }

    public QStyleCategory(Path<? extends StyleCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStyleCategory(PathMetadata metadata) {
        super(StyleCategory.class, metadata);
    }

}

