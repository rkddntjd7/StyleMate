package project.stylemate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStyleItem is a Querydsl query type for StyleItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStyleItem extends EntityPathBase<StyleItem> {

    private static final long serialVersionUID = 673228454L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStyleItem styleItem = new QStyleItem("styleItem");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath accessoryItem = createString("accessoryItem");

    public final StringPath bottomItem = createString("bottomItem");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDateTime = _super.createDateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath shoesItem = createString("shoesItem");

    public final QStyle style;

    public final StringPath topItem = createString("topItem");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDateTime = _super.updateDateTime;

    public QStyleItem(String variable) {
        this(StyleItem.class, forVariable(variable), INITS);
    }

    public QStyleItem(Path<? extends StyleItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStyleItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStyleItem(PathMetadata metadata, PathInits inits) {
        this(StyleItem.class, metadata, inits);
    }

    public QStyleItem(Class<? extends StyleItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.style = inits.isInitialized("style") ? new QStyle(forProperty("style"), inits.get("style")) : null;
    }

}

