try {
  rs.status();
} catch (err) {
  rs.initiate({
    _id: 'rs0',
    members: [{
      _id: 0,
      host: 'host.docker.internal:27017'
    }]
  });
  quit(1);
}

db = db.getSiblingDB(process.env.MONGO_INITDB_DATABASE);

if (db.getCollectionNames().indexOf('usuario') > -1) {
  print('Database already intitialized. Exiting...');
  quit(0);
}

db.createCollection('usuario');

db.usuario.insertMany([
  {
    email: 'admin@test.com',
    contrasenia: '$2a$10$5y2oLIb8yVqxy0A75AmstOKQEoq/xLDAbF3cKNw1Cj4o1NVoK1QEW',
    nombreDeUsuario: 'admin',
    roles: ['ADMIN'],
    _class: 'ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario'
  },
  {
    email: 'usuario@test.com',
    contrasenia: '$2a$10$mE7T09BW3Qxwnd/20h5JW.sp2w47T0DFoXGlxsDHda2vLLjpzgsbG',
    nombreDeUsuario: 'user',
    roles: ['USUARIO'],
    _class: 'ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario'
  }
]);
