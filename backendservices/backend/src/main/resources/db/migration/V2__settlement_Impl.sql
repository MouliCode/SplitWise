CREATE TABLE settlements (
    id UNIQUEIDENTIFIER NOT NULL,
    from_user UNIQUEIDENTIFIER NOT NULL,
    to_user UNIQUEIDENTIFIER NOT NULL,
    amount DECIMAL(18,2) NOT NULL,
    created_at DATETIME2 DEFAULT SYSDATETIME(),

    CONSTRAINT pk_settlements PRIMARY KEY (id),

    CONSTRAINT fk_settlements_from
        FOREIGN KEY (from_user) REFERENCES users(id),

    CONSTRAINT fk_settlements_to
        FOREIGN KEY (to_user) REFERENCES users(id),

    CONSTRAINT chk_settlement_amount
        CHECK (amount > 0)
);
