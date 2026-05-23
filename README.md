# 📁 QuikFile

**QuikFile** es una aplicación Android nativa para organizar archivos personales, colaborar en entornos compartidos y gestionar opciones de cuenta desde una única interfaz.

## 📌 Estado del proyecto

Proyecto académico orientado a prototipar la experiencia de una app de almacenamiento y gestión de archivos en Android.

## ✨ Funcionalidades principales

### Acceso y cuenta
- Inicio de sesión, registro y recuperación de contraseña.
- Ajustes de cuenta con cambio de nombre, correo y contraseña.
- Cambio de idioma entre español e inglés.

### Gestión de archivos
- Vista principal de archivos y carpetas personales.
- Creación de carpetas, subcarpetas y ficheros.
- Consulta de detalles del archivo: tamaño, formato, extensión y versión actual.
- Previsualización, descarga, renombrado y reporte de incidencias.

### Colaboración
- Entorno compartido para trabajar con otros usuarios.
- Alta de miembros con roles de administrador o miembro.
- Configuración del entorno compartido y gestión de permisos.
- Pantallas dedicadas para compartir archivos y revisar historial de versiones.

### Soporte y suscripción
- Centro de soporte con FAQs, manual de usuario y contacto.
- Gestión de plan premium, renovación, cancelación y datos de pago.
- Pantallas de reportes de aplicación y de archivo.

### Papelera
- Envío de elementos eliminados a la papelera.
- Recuperación o eliminación permanente.
- Gestión centralizada mediante `TrashActivity` y `TrashManager`.

## 🛠️ Stack técnico

| Tecnología | Detalle |
|---|---|
| Lenguaje | Java 11 |
| Plataforma | Android |
| SDK mínimo | API 33 (Android 13) |
| SDK compilación / objetivo | API 36 |
| UI | Android Views + XML |
| Diseño | Material Design 3 |
| Build system | Gradle con Kotlin DSL |

### Dependencias principales
- `androidx.appcompat`
- `com.google.android.material`
- `androidx.activity`
- `androidx.constraintlayout`
- `junit`
- `androidx.test.ext:junit`
- `androidx.test.espresso:espresso-core`

## 🧱 Estructura del proyecto

```text
QuikFile/
├── app/
│   ├── src/main/java/com/example/quikfile/
│   │   ├── LoginActivity.java
│   │   ├── RegisterActivity.java
│   │   ├── MainActivity.java
│   │   ├── SharedActivity.java
│   │   ├── TrashActivity.java
│   │   ├── SettingsActivity.java
│   │   ├── SupportActivity.java
│   │   ├── FileDetailActivity.java
│   │   ├── FilePreviewActivity.java
│   │   ├── FileShareActivity.java
│   │   ├── FileVersionHistoryActivity.java
│   │   └── ...
│   ├── src/main/res/layout/          # Layouts XML de las pantallas
│   ├── src/main/res/values/          # Strings, estilos y temas
│   └── src/test/ y src/androidTest/  # Tests base del proyecto
├── gradle/libs.versions.toml         # Catálogo de versiones
├── build.gradle.kts                  # Configuración raíz
└── settings.gradle.kts               # Módulos del proyecto
```

## 🚀 Puesta en marcha

### Requisitos
- Android Studio con soporte para proyectos Android actuales.
- JDK 11.
- Android SDK Platform 36 y un emulador/dispositivo con Android 13 o superior.

### Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/ALVARORCCM18/QuikFile.git
   cd QuikFile
   ```
2. Abre el proyecto en **Android Studio**.
3. Espera a que Gradle sincronice el módulo `app`.
4. Ejecuta la aplicación en un emulador o dispositivo físico.

## ✅ Comandos útiles

Desde la raíz del repositorio:

```bash
./gradlew assembleDebug
./gradlew testDebugUnitTest
./gradlew lintDebug
```

## 👥 Autores

- Abad Alejandro
- Robador Gadea
- Sampedrano Iker
- Rodríguez Álvaro

## 📄 Licencia

Este proyecto es de uso académico.
