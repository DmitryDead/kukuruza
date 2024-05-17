JFLAGS = -g
JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES =\
	ExceptionM.java\
	Bonus.java\
	Asteroid.java\
	KeyBM.java\
	Bullet.java\
	Player.java\
	Kreep.java\
	StarField.java\
	GameScreen.java\
	Main.java
default: classes
classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
