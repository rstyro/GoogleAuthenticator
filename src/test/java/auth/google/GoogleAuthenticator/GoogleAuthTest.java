package auth.google.GoogleAuthenticator;

import java.io.IOException;

import org.junit.Test;

import com.google.zxing.WriterException;

/**
 * Unit test for simple App.
 */
public class GoogleAuthTest {
	public static String secretKey="LE44GDHRFGAAU54S2RZJW6PQKRNTSKZI";
	
	@Test
	public void testgenerateSecretKeyCode() throws WriterException, IOException {
		String secretKey = GoogleAuthenticator.getRandomSecretKey();
		String barcode = GoogleAuthenticator.getGoogleAuthenticatorBarCode(
				secretKey, "rstyro@google.com", "rstyro");
		//PL2FOP7QE7ZVLK5D2MRNHWVVV2G7573G
		System.out.println("secretkey:" + secretKey);
		System.out.println("barcode:" + barcode);
		/**
		 * 生成一个secret 并生成一个 校验地址
		 * secretkey:LE44GDHRFGAAU54S2RZJW6PQKRNTSKZI
		   barcode:otpauth://totp/rstyro%3Arstyro%40google.com?secret=LE44GDHRFGAAU54S2RZJW6PQKRNTSKZI&issuer=rstyro
		 */
		//生成二维码
		GoogleAuthenticator.createQRCode(barcode, "G://code.png", 200, 200);
	}

	@Test
	public void testcheckCode() {
		long code = 352344;
		//获取验证码
		code = Long.valueOf(GoogleAuthenticator.getTOTPCode(secretKey));
		System.out.println("code="+code);
		//校验是否匹配
		System.out.println("isTrue="+ GoogleAuthenticator.check_code(secretKey, code,System.currentTimeMillis()));
	}
	
	
	////////////////////////////////    Goodle2 ,都差不多，就稍微改了下    //////////////////////////////////////////////////////////

	@Test
	public void genSecretTest() {// 生成密钥
		String secret = GoogleAuthenticator2.generateSecretKey();
		// 把这个qrcode生成二维码，用google身份验证器扫描二维码就能添加成功
		String qrcode = GoogleAuthenticator2.getQRBarcode("1006059906@qq.com",
				secret);
		System.out.println("qrcode:" + qrcode + ",key:" + secret);
	}

	/**
	 * 对app的随机生成的code,输入并验证
	 */
	@Test
	public void verifyTest() {
		String secret ="5VIKK7JOX4BHRR7R";
		long code = 377090;
		long t = System.currentTimeMillis();
		GoogleAuthenticator2 ga = new GoogleAuthenticator2();
		ga.setWindowSize(5);
		boolean r = ga.check_code(secret, code, t);
		System.out.println("检查code是否正确？" + r);
	}
}
