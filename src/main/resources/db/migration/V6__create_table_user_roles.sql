CREATE TABLE UserRoles (
  role_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES Roles(id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
  PRIMARY KEY (role_id, user_id)
);