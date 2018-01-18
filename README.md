# studymp

This project is base configuration of web-app with Authentication based on Spring-Security 
and the elimination of the most experienced vulnerabilities like XSS, CSRF, BruteForce and others.

To demonstrate the work of the application you should import it as maven project and choose profile with embedded H2 database (embedded) in application.properties (or configure database connection in PersistenceJPAConfig).

Current version include email password-reset/registration. Configure of using email for no-reply messages you can see in confirmMail.properties file.
