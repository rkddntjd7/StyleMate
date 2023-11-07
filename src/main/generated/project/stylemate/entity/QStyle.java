package project.stylemate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStyle is a Querydsl query type for Style
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStyle extends EntityPathBase<Style> {

    private static final long serialVersionUID = -1852741389L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStyle style = new QStyle("style");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDateTime = _super.createDateTime;

    public final DateTimePath<java.time.LocalDateTime> deleteDateTime = createDateTime("deleteDateTime", java.time.LocalDateTime.class);

    public final EnumPath<project.stylemate.enums.Gender> gender = createEnum("gender", project.stylemate.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxHeight = createNumber("maxHeight", Integer.class);

    public final QMember member;

    public final NumberPath<Integer> minHeight = createNumber("minHeight", Integer.class);

    public final StringPath styleCategory = createString("styleCategory");

    public final StringPath styleImages = createString("styleImages");

    public final NumberPath<Long> styleRank = createNumber("styleRank", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDateTime = _super.updateDateTime;

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QStyle(String variable) {
        this(Style.class, forVariable(variable), INITS);
    }

    public QStyle(Path<? extends Style> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStyle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStyle(PathMetadata metadata, PathInits inits) {
        this(Style.class, metadata, inits);
    }

    public QStyle(Class<? extends Style> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

