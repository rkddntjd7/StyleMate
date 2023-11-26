package project.stylemate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStyleImage is a Querydsl query type for StyleImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStyleImage extends EntityPathBase<StyleImage> {

    private static final long serialVersionUID = -604966872L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStyleImage styleImage = new QStyleImage("styleImage");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDateTime = _super.createDateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgPath = createString("imgPath");

    public final QStyle style;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDateTime = _super.updateDateTime;

    public QStyleImage(String variable) {
        this(StyleImage.class, forVariable(variable), INITS);
    }

    public QStyleImage(Path<? extends StyleImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStyleImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStyleImage(PathMetadata metadata, PathInits inits) {
        this(StyleImage.class, metadata, inits);
    }

    public QStyleImage(Class<? extends StyleImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.style = inits.isInitialized("style") ? new QStyle(forProperty("style"), inits.get("style")) : null;
    }

}

