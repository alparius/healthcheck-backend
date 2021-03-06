CREATE TABLE IF NOT EXISTS Hospital(id SERIAL PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      city VARCHAR(50) NOT NULL,
      address VARCHAR(255) NOT NULL);

CREATE TABLE IF NOT EXISTS User(id serial PRIMARY KEY,
      username VARCHAR(50) UNIQUE NOT NULL,
      password VARCHAR(100) NOT NULL,
      email VARCHAR(100) UNIQUE NOT NULL,
      phone VARCHAR(15) UNIQUE,
      firstName VARCHAR(100),
      surname VARCHAR(100),
      city VARCHAR(100),
      isAdmin BOOLEAN,
      hospital BIGINT UNSIGNED,
      FOREIGN KEY(hospital) REFERENCES Hospital(id) ON DELETE SET NULL);

CREATE TABLE IF NOT EXISTS Proposal(id serial PRIMARY KEY,
      proposedBy VARCHAR(50) NOT NULL,
      description VARCHAR(255) NOT NULL,
      category VARCHAR(50),
      title VARCHAR(100),
      status VARCHAR(10) NOT NULL,
      startDateAndTime TIMESTAMP NOT NULL,
      endDateAndTime TIMESTAMP NOT NULL,
      hospital BIGINT UNSIGNED,
      FOREIGN KEY(hospital) REFERENCES Hospital(id) ON DELETE CASCADE,
      FOREIGN KEY(proposedBy) REFERENCES User(username) ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS Activity(id serial PRIMARY KEY,
      description VARCHAR(255) NOT NULL,
      category VARCHAR(50) NOT NULL,
      title VARCHAR(100) NOT NULL,
      status VARCHAR(12) NOT NULL,
      startDateAndTime TIMESTAMP NOT NULL,
      endDateAndTime TIMESTAMP NOT NULL,
      hospital BIGINT UNSIGNED,
      FOREIGN KEY(hospital) REFERENCES Hospital(id) ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS Report(id serial PRIMARY KEY,
      description VARCHAR(255) NOT NULL,
      category VARCHAR(50) NOT NULL,
      title VARCHAR(100) NOT NULL,
      text VARCHAR(255) NOT NULL,
      activity BIGINT UNSIGNED,
      FOREIGN KEY(activity) REFERENCES Activity(id) ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS Activity_User(id serial PRIMARY KEY,
      activity BIGINT UNSIGNED NOT NULL,
      user BIGINT UNSIGNED NOT NULL,
      UNIQUE(activity, user),
      FOREIGN KEY(activity) REFERENCES Activity(id) ON DELETE CASCADE,
      FOREIGN KEY(user) REFERENCES User(id) ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS Vote(user BIGINT UNSIGNED NOT NULL,
      proposal BIGINT UNSIGNED NOT NULL,
      PRIMARY KEY(user, proposal),
      FOREIGN KEY(user) REFERENCES User(id) ON DELETE CASCADE,
      FOREIGN KEY(proposal) REFERENCES Proposal(id) ON DELETE CASCADE)
