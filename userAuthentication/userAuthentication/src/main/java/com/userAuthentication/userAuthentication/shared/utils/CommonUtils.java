package com.userAuthentication.userAuthentication.shared.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

public class CommonUtils {

	public static long getCurrentTime() {
		Date date = new Date();
		return date.getTime();
	}

	public static String getIfExpression(String expression, Map<String, Double> valueMap) {

		List<String> variableList = new ArrayList<>();
		String regexExpression = "(?<=&&|>=|<=|!=|==|[()\\[\\]+\\-*/><])|(?=&&|>=|<=|!=|==|[()\\[\\]+\\-*/><])";
		if (expression != null && expression.length() > 0) {
			variableList.addAll(Arrays.asList(expression.split(regexExpression)));
		}

		for (String keyName : valueMap.keySet()) {
			keyName = keyName.trim();
//			System.out.println("Key Name :> " + keyName);

			for (String splitKeyname : variableList) {
				splitKeyname = splitKeyname.trim();
//				System.out.println("Split Key Name :> " + splitKeyname);

				if (splitKeyname.equalsIgnoreCase(keyName)) {
					String keyValue = valueMap.get(keyName) + "";
					expression = expression.replace(keyName, keyValue);
					break;
				}
			}
		}
		return expression;
	}

	public static boolean checkExpression(String formula) {
		boolean isValid = false;
		if (formula != null) {
			ExpressionParser parser = new SpelExpressionParser();
			StandardEvaluationContext context = new StandardEvaluationContext();
			isValid = parser.parseExpression(formula).getValue(context, Boolean.class);
		}

		return isValid;
	}

	public static boolean validateEmailFormat(String email) {
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean validateMobileNumber(String mobile) {
		if (!(StringUtils.hasLength(mobile))) {
			return false;
		}
		if (mobile.length() != 10) {
			return false;
		}
		if (mobile.matches("[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}

	public static String getDoubleStringValue(Double value) {
		String plainNumber = "";
		if (value != null) {

			BigDecimal number = new BigDecimal(value);
			plainNumber = number.toPlainString();
		}

		return plainNumber;
	}

	public static long generateOTP() {
		Random random = new Random();
		long otp = 100000 + random.nextInt(99999);
		return otp;
	}

	public static ResourceBundle getResourceBundle(String resourceFileName) {
		return ResourceBundle.getBundle(resourceFileName, Locale.US);
	}

}
