package javatest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * パスワード作成の複雑さを設定でき。
 * 実施する結果：
 * 
 * strength 4 140ms $2a$04$QkBpDKj2wGbp92Zd.uv9NutZfDUoV.zsB/iiOid2OdQ57NQrjbg.W
 * strength 5 16ms $2a$05$uv0HhkdP4xDSPby8cB63Rul8En0IXsHNdHP/ux1XifQUNJgb/nX6q
 * strength 6 15ms $2a$06$rxBVV9TFzCmE.KLaGWIhw.UUfoXAFqVQbJPWrG/ba9qhKsZTa1s.i
 * strength 7 16ms $2a$07$BZZMl5Oq4nqS9n9MHUe/tOnALDzgjoY9zcaTREL9mYxaAV420tXCO
 * strength 8 16ms $2a$08$g/QxA6kVD6n1TQkmvfQSiOIzbFoLp9WQRmOl/ViYi.o5vgIwcb8QO
 * strength 9 62ms $2a$09$q.s0Z5B/b9OAupv4VdC/C.SBaodT9dkndqPcJyJjvv3UMFzJKYAsG
 * strength 10 94ms $2a$10$0qzeIbne1djEX.lIJdk1s.HKXojlQQTnK1M70QBQfj480TE2Rvgx6
 * strength 11 202ms $2a$11$GYAELLKry7uOyFOQ5JCcae15wAJUdAWvdeLcsqGGl64xueio4mq/K 
 * strength 12 406ms $2a$12$4uuIY3tlk5yoZlTFHy5I1ufzb79mg5IKvaGHZ9TmAM0sD/Hq9clui 
 * strength 13 798ms $2a$13$jJInE9li7iaCl/T38eLkYuYOq5MofC2WQuw6a4O8tzksYrhOlpKh.
 * strength 14 1685ms $2a$14$5XiqBCr1BMmH0wM8F7HS7.Ncu3CYEwgcmrkR6hHeYhw.2sPDrIIEm 
 * strength 15 3198ms $2a$15$bCxC4.pB7S6ETGWI1IEwiOrshrIGDIaZYuAQjrwyoqFAEpvIkJj2m 
 * strength 16 6418ms $2a$16$2jb0/RWHS0CGy.gftgc4Tu26SWN9s15fTUBlEGpt4MOTHGeMqU70i
 * strength 17 13011ms $2a$17$jcRa1UjSvOmbrpHJ3skji.Iy/g97afAj/V0ifkTgrmS6e51JQDqGe 
 * strength 18 25831ms $2a$18$Xi6Fu7yBZt0cm7mizxkA7ulZgBj3Fffo6.zFt1U1aQMyC1At2RRXG 
 * strength 19 51469ms $2a$19$q0EKvwacKtPTFryQss.JxOiP1PChw.9KwuLC/DbuR1fmKFIwHMlom
 * 
 */

public class PasswordEncodeBcrypt {

	public static void main(String[] args) {
		encode();
	}

	static void encode() {
		String pass = "1111";

		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		String hash = passEncoder.encode(pass);
		System.out.println(hash);
	}

	static void encodeWithStrength() {
		String rawPass = "tienlv";
		long t1, t2 = System.currentTimeMillis();
		BCryptPasswordEncoder passEncoder;
		String hash;

		int i = 3;
		do {
			i++;
			t1 = t2;
			passEncoder = new BCryptPasswordEncoder(i);
			hash = passEncoder.encode(rawPass);
			t2 = System.currentTimeMillis();
			System.out.printf("strength %d \t %dms\t %s \n", i, t2 - t1, hash);
		} while (i < 31);

	}

}
