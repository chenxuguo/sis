package sis.testing;

import java.lang.annotation.*;

public @interface Date {
	int month();
	int day();
	int year();
}
