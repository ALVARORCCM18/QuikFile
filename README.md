# 📁 QuikFile

**QuikFile** es una aplicación Android de gestión de archivos que permite a los usuarios organizar, compartir y administrar archivos y carpetas de forma sencilla e intuitiva.

## ✨ Características

### 🔐 Autenticación
- Inicio de sesión con usuario y contraseña.
- Registro de nuevos usuarios con validación de campos y confirmación de contraseña.
- Recuperación de contraseña desde la pantalla de acceso.

### 🏠 Gestión de archivos personales
- Visualización de archivos y carpetas en formato de cuadrícula.
- Creación de nuevas carpetas y archivos desde la pantalla principal.

### 🤝 Entorno compartido
- Espacio colaborativo para compartir archivos y carpetas con otros usuarios.
- Gestión de miembros: añadir participantes al entorno compartido.
- Configuración del entorno: cambiar nombre, salir o eliminar el espacio compartido.

### 🗑️ Papelera
- Los archivos eliminados se mueven a la papelera.
- Selección individual o masiva de archivos.
- Opciones para recuperar o eliminar permanentemente archivos.

### ⚙️ Ajustes
- Edición del nombre del perfil.
- Cambio de foto de perfil.
- Configuración de la cuenta y plan de pago.
- Informes y analíticas de la aplicación.
- Acceso a opciones de soporte y ayuda para el usuario.

### 🆘 Soporte y ayuda
- Centro de ayuda con preguntas frecuentes (FAQ).
- Pantalla de contacto para soporte.
- Manual de usuario accesible desde ajustes.
- Gestión de cancelación del plan desde la app.

## 🛠️ Tecnologías

| Tecnología | Detalle |
|---|---|
| **Lenguaje** | Java |
| **SDK mínimo** | API 33 (Android 13) |
| **SDK objetivo** | API 36 |
| **Tema** | Material Design 3 (DayNight) |
| **Build system** | Gradle (Kotlin DSL) |

### Dependencias principales
- `androidx.appcompat` — Compatibilidad con versiones anteriores de Android.
- `com.google.android.material` — Componentes de Material Design.
- `androidx.activity` — API moderna para Activities.
- `androidx.constraintlayout` — Layouts flexibles y responsivos.

## 📂 Estructura del proyecto

```
app/src/main/
├── java/com/example/quikfile/
│   ├── LoginActivity.java          # Pantalla de inicio de sesión
│   ├── RegisterActivity.java       # Pantalla de registro
│   ├── MainActivity.java           # Pantalla principal (archivos personales)
│   ├── AddActivity.java            # Diálogo para crear carpeta/archivo
│   ├── SharedActivity.java         # Entorno de archivos compartidos
│   ├── SharedSettingsActivity.java # Ajustes del entorno compartido
│   ├── TrashActivity.java          # Papelera de archivos
│   ├── SettingsActivity.java       # Ajustes del usuario
│   ├── ForgotPasswordActivity.java # Recuperación de contraseña
│   ├── FaqActivity.java            # Preguntas frecuentes (FAQ)
│   ├── ContactActivity.java        # Contacto de soporte
│   ├── UserManualActivity.java     # Manual de usuario
│   └── CancellationActivity.java   # Cancelación del plan
└── res/
    ├── layout/                     # Layouts XML de cada pantalla
    ├── drawable/                   # Iconos e imágenes vectoriales
    └── values/                     # Strings, temas y estilos
```

## 🚀 Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/ALVARORCCM18/QuikFile.git
   ```
2. Abre el proyecto en **Android Studio**.
3. Sincroniza las dependencias de Gradle.
4. Ejecuta la aplicación en un emulador o dispositivo con **Android 13 (API 33)** o superior.

## 👥 Autores

- Abad Alejandro
- Robador Gadea
- Sampedrano Iker
- Rodríguez Álvaro

## 📄 Licencia

Este proyecto es de uso académico.
