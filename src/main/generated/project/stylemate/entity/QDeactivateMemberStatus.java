package project.stylemate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeactivateMemberStatus is a Querydsl query type for DeactivateMemberStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeactivateMemberStatus extends EntityPathBase<DeactivateMemberStatus> {

    private static final long serialVersionUID = -261561666L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeactivateMemberStatus deactivateMemberStatus = new QDeactivateMemberStatus("deactivateMemberStatus");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDateTime = _super.createDateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final EnumPath<project.stylemate.enums.MemberStatus> status = createEnum("status", project.stylemate.enums.MemberStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDateTime = _super.updateDateTime;

    public QDeactivateMemberStatus(String variable) {
        this(DeactivateMemberStatus.class, forVariable(variable), INITS);
    }

    public QDeactivateMemberStatus(Path<? extends DeactivateMemberStatus> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeactivateMemberStatus(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeactivateMemberStatus(PathMetadata metadata, PathInits inits) {
        this(DeactivateMemberStatus.class, metadata, inits);
    }

    public QDeactivateMemberStatus(Class<? extends DeactivateMemberStatus> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

