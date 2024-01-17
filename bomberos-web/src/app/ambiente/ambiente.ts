export const ambiente = {
    baseUrl: '/bomberos_app/',
    endpoint: {
      // LOGIN
      login: 'bomberos_rest/login',
      info: 'bomberos_rest/login/info',
      logout: 'bomberos_rest/logout',
  
      // UNIDADES
      allunidades: 'bomberos_rest/unidad/allunidades',
      unidadbyid: 'bomberos_rest/unidad/unidadbyid?',
      unidadcompletabyid: 'bomberos_rest/unidad/unidadcompletabyid?',
      deleteunidad: 'bomberos_rest/unidad/delete',
      nueva_unidad: 'bomberos_rest/unidad/nueva-unidad',

      // USERS
      getusers: 'bomberos_rest/user/get-users',
      bajauser: 'bomberos_rest/user/baja-user?',

      // REGISTROS
      allregistros: 'bomberos_rest/registro/getregistros?',
      nuevoregistro: 'bomberos_rest/registro/nuevoregistro',
    }
  }
