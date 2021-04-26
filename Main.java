package Main;

import java.io.*;
import java.util.*;


class Ticket{
	int days;
	int number;
	String type;
	String buyDate;
	int remainDays,remainNum;
	public Ticket(int days, String type) {
		super();
		this.days = days;
		this.number = days;
		this.type = type;
		this.remainDays = days;
		this.remainNum = days;

	}
	public void setDate(String date) {
		this.buyDate = date;
	}


}

class Bus {
	String startingPoint; //5가지
	String destination; //5가지
	String startDate; //1일(당일)
	String startTime; //1가지
	int people = 0;
	boolean[] seat = new boolean[10];


	public Bus(String startingPoint, String destination, String startTime) {
		super();
		this.startingPoint = startingPoint;
		this.destination = destination;
		this.startTime = startTime;
	}




}

class Point{
	String date,time;
	int money, point;
	public Point(String date, String time, int money, int point) {
		super();
		this.date = date;
		this.time = time;
		this.money = money;
		this.point = point;
	}

}

class Member{
	String name;
	String birth;
	String phoneNum;
	String id;
	String pwd;
	String question,answer;
	Point point;
	Ticket[] ticket = new Ticket[10];
	String date,time;
	ArrayList<Bus> b = new ArrayList<Bus>();
	int ticketCnt;


	public Member(String name, String birth, String phoneNum, String id, String pwd, String question, String answer) {
		super();
		this.name = name;
		this.birth = birth;
		this.phoneNum = phoneNum;
		this.id = id;
		this.pwd = pwd;
		this.question = question;
		this.answer = answer;
	}
	int cnt = 0;
	public void addTicket(Ticket ticket) {
		this.ticket[cnt++] = ticket;
	}
}



public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static ArrayList<Member> list = new ArrayList<Member>();
	public static ArrayList<Bus> bus = new ArrayList<Bus>();
	public static Scanner sc =new Scanner(System.in);
	public static String[] depPoint = {"서울","인천","대구","부산","광주"};
	public static String[] arrPoint = {"서울","인천","대구","부산","광주"};
	public static String[] time = {"7:00","10:00","13:00","16:00","19:00","22:00"};
	public static String[] qarr = {"1. 나의 가장 친한 친구 이름은?","2. 내가 가장 존경하는 인물은?","3. 내가 졸업한 초등학교는?"};
	public static int count = 0;
	public static String nowDate; //오늘 날짜
	public static String nowTime; //현재 시간
	public static void main(String[] args) throws Exception {
		saveBusData();
		int val = printMenu();
		if(val==3) return;


	}




	public static void saveBusData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(i!=j) {
					for (int k = 0; k < time.length; k++) {
						bus.add(new Bus(depPoint[i],arrPoint[j],time[k]));

					}
				}
			}
		}
		for (int i = 0; i < bus.size(); i++) {
			Arrays.fill(bus.get(i).seat, false);
		}
	}





	public static int printMenu() throws Exception {
		// TODO Auto-generated method stub
		int in=0;
		while(true) {
			System.out.println("1. 회원가입    2. 로그인    3. 종료");
			in = Integer.parseInt(br.readLine());
			switch(in) {
			case 1:
				joinMember();
				break;
			case 2:
				Login();
				break;
			case 3:
				return 3;
			default:
				System.out.println("다시 입력해주세요.");
			}

		}

	}




	public static void Login() throws Exception {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("==========로그인 메뉴==========");
			System.out.println("1. 로그인    2. 아이디 찾기    3. 비밀번호 찾기");
			int in;
			in = Integer.parseInt(br.readLine());
			switch(in) {
			case 1:
				loginMain();
				break;
			case 2:
				findID();
				break;
			case 3:
				findPWD();
				break;
			default:
				System.out.println("다시 입력해주세요.");
			}
		}


	}




	public static void findPWD() throws Exception {
		// TODO Auto-generated method stub
		while(true) {
			System.out.print("아이디를 입력하세요:");
			String id;
			id = br.readLine();
			int val = findinfo(id);
			if(val==1) {
				System.out.println("해당 아이디는 존재하지 않습니다.");
			}else {
				break;
			}
		}

	}


	public static void findID() throws Exception {
		System.out.println("==========ID 찾기==========");
		String name, phoneNum, birth, id = null;
		while(true) {
			while(true) {
				System.out.print("이름을 입력하세요:");
				name = br.readLine();
				if(name.charAt(0)==' ') {
					System.out.println("잘못된 입력입니다. 선행 공백을 허용하지 않습니다.");
					continue;
				}
				int al = nameisAlpa(name);
				if(al==1) {
					System.out.println("이름은 한글 입력만 허용합니다.");   
					continue;
				}else
					break;

			}
			while(true) {
				System.out.print("생년월일을 입력하세요:");
				birth = br.readLine();
				if(name.charAt(0)==' ') {
					System.out.println("잘못된 입력입니다. 선행 공백을 허용하지 않습니다.");
					continue;
				}
				break;
			}
			while(true) {
				System.out.print("핸드폰 번호를 입력하세요:");
				phoneNum = br.readLine();
				if(phoneNum.charAt(0)!='0'||phoneNum.charAt(1)!='1'||phoneNum.charAt(2)!='0') {
					System.out.println("맨 앞의 숫자 세 개는 010만 허용합니다.");
					continue;
				}
				if(phoneNum.charAt(3)=='-') {
					String[] num = phoneNum.split("-");
					phoneNum = num[0]+num[1]+num[2];
				}else if(phoneNum.charAt(3)=='.') {
					String[] num = new String[3];   
					for (int i = 0; i < num.length; i++) {
						num[i] = "";
					}
					for (int i = 0; i < 3; i++) {
						num[0] += Character.toString(phoneNum.charAt(i));
					}
					for (int i = 4; i < 8; i++) {
						num[1] += Character.toString(phoneNum.charAt(i));
					}
					for (int i = 9; i < 13; i++) {
						num[2] += Character.toString(phoneNum.charAt(i));
					}
					phoneNum = num[0]+num[1]+num[2];
				}else if(phoneNum.charAt(3)=='_') {
					String[] num = phoneNum.split("_");
					phoneNum = num[0]+num[1]+num[2];
				}
				break;
			}
			int k = 0;
			for(int i=0; i<list.size();i++) {
				if(list.get(i).name.equals(name)&&list.get(i).birth.equals(birth)&&list.get(i).phoneNum.equals(phoneNum)) {
					id = list.get(i).id;
					k=1;
				}
				else {
					k=2;
				}
			}
			if(k==1) {
				System.out.println("아이디:"+id);
				break;
			}
			if(k==2) {
				System.out.println("일치하는 아이디가 없습니다.");
				continue;
			}  
		}
	}

	public static int findinfo(String id) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).id.equals(id)) {
				while(true) {
					switch(Integer.parseInt(list.get(i).question)) {
					case 1:
						System.out.println("1. 나의 가장 친한 친구 이름은?");
						break;
					case 2:
						System.out.println("2. 내가 가장 존경하는 인물은?");
						break;
					case 3:
						System.out.println("3. 내가 졸업한 초등학교는?");
						break;

					}
					String an = br.readLine();
					if(list.get(i).answer.equals(an)) {
						System.out.println("비밀번호:"+list.get(i).pwd);

					}else {
						System.out.println("답이 일치하지 않습니다.");

					}
					return 0;
				}

			}

		}
		return 1;
	}




	public static void loginMain() throws Exception {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("==========로그인 메뉴==========");
			System.out.print("아이디:");
			String id = br.readLine();
			System.out.print("비밀번호:");
			String pwd = br.readLine();
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).id.equals(id)) {
					if(list.get(i).pwd.equals(pwd))
					{
						startMenu(list.get(i));
						break;
					}

				}
			}
			System.out.println("아이디/비밀번호를 잘못입력하셨습니다.");
		}
	}




	public static void startMenu(Member member) throws Exception {
		while(true) {
			System.out.println("==========메 뉴==========");
			System.out.println("1. 예매    2. 정기권 구매    3. 마이페이지");
			int in = Integer.parseInt(br.readLine());
			switch(in) {
			case 1:
				reserve(member);
				break;
			case 2:
				buyTicket(member);
				break;
			case 3:
				mypageMenu(member);
				break;

			}
		}

	}
	public static void mypageMenu(Member member) throws Exception{
		int in;
		while(true) {
			System.out.println("1. 회원 정보 수정    2. 포인트 조회    3. 결제내역 조회    4. 보유중인 정기권 확인    5. 예매내역 조회 및 취소    6. 회원탈퇴");
			in = Integer.parseInt(br.readLine());
			switch(in) {
			case 1:
				reviseMember(member);
				break;
			case 2:
				showPoint(member);
				break;
			case 3:
				showBill(member);
				break;
			case 4:
				showTickets(member);
				break;
			case 5:
				checkReservation(member);
				break;
			case 6:
				System.out.println("정말로 버스 예약 프로그램을 탈퇴하시겠습니까?(1. 예    2. 아니오)");
				int val = Integer.parseInt(br.readLine());
				if(val==1) {
					int idx = list.indexOf(member);
					list.remove(idx);
					System.exit(0);
					break;
				}else {
					break;
				}
			default:
				System.out.println("다시 입력해주세요.");
				break;
			}

		}

	}

	public static void checkReservation(Member member) {
		// TODO Auto-generated method stub

	}




	public static void showBill(Member member) {
		// TODO Auto-generated method stub
		if(count>0) {
			File file = new File("Point"+member.id+".txt");
			ArrayList<String> bill = new ArrayList<>();
			try{
				if(checkBeforeFile(file)){
					BufferedReader br = new BufferedReader(new FileReader(file));

					String str = br.readLine();

					while(str != null){
						String[] st = str.split(" ");
						String s="";
						for (int i = 0; i < st.length; i++) {
							if(i!=3) {
								s+=st[i]+" ";
							}
						}
						bill.add(s);
						str = br.readLine();

					}
					for (int i = 0; i < bill.size(); i++) {
						System.out.println(bill.get(i));
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}




	public static void showTickets(Member member) {
		// TODO Auto-generated method stub
		System.out.println("==========정기권 확인==========");
		for (int i = 0; i < member.ticketCnt; i++) {
			Ticket ticket = member.ticket[i];
			if(ticket.type.equals("day")) {
				System.out.println("정기권 종류: 기간제");
				System.out.println("정기권 유효 기간: "+ticket.remainDays);
			}else if(ticket.type.equals("num")) {
				System.out.println("정기권 종류: 횟수제");
				System.out.println("정기권 남은 횟수: "+ticket.remainNum);
			}
		}
	}




	public static void reviseMember(Member member) throws Exception {
		System.out.println("==========회원 정보 수정==========");
		char selectnum;
		String select;
		String newname, newphoneNum, newid, newpwd, newq, newa, newbirth;
		String temppwd;
		while(true) {
			System.out.print("본인 확인을 위해 비밀번호를 한번 더 입력하세요:");
			temppwd = br.readLine();
			if(temppwd.length()>=5&&temppwd.length()<=15) {
				int val = pwdCheck(temppwd);
				if(val==1) {
					System.out.println("비밀번호는 영소문자/숫자/특수문자로 입력되어야 합니다.");
					continue;
				}
				break;
			}else {
				System.out.println("비밀번호는 5-15자 이내에 입력해야합니다.");
				continue;
			}
		}
		if(temppwd.equals(member.pwd)) {
			System.out.println("현재 아이디 : "+member.id);
			System.out.println("현재 비밀번호 : "+member.pwd);
			System.out.println("현재 이름 : "+member.name);
			System.out.println("현재 전화번호 : "+member.phoneNum);
			System.out.println("현재 생년월일 : "+member.birth);
			System.out.println("현재 질문 : "+ qarr[Integer.parseInt(member.question)-1]);
			System.out.println("현재 질문의 답 : "+member.answer);

			System.out.println("아이디, 비밀번호, 이름, 전화번호, 질문, 답 중에서 수정을 원하는 정보명을 입력하세요.");
			select=sc.next();
			//공백문자열이 있는지 체크
			if(checkIsBlank(select)==1) {
				return; 
			}
			//한글 문자열인지 체크
			for(int i=0; i<select.length();i++) {
				if(Character.getType(select.charAt(i))!=5) {
					System.out.println("1글자 이상 4글자 이하 한글 문자열를 입력하세요.");
					mypageMenu(member);
					return;
				}
			}

			if(select.length()<1 || select.length()>4) {
				System.out.println("1글자 이상 4글자 이하 한글 문자열를 입력하세요.");
				mypageMenu(member);
				return;
			}


			if(select.equals("아이디")) {
				while(true) {
					System.out.println("현재 아이디 : "+member.id+" 새로운 아이디를 입력하세요 : ");
					newid = br.readLine();
					int k =0;
					for(int i=0; i<list.size();i++) {
						if(list.get(i).id.equals(newid)) {
							k=1;
						}
					}
					for(int i=0; i<newid.length();i++) {
						if(newid.length()>2 && newid.length()<21){
							if(Character.isDigit(newid.charAt(i))||Character.isLowerCase(newid.charAt(i))) {

							}
							else {
								k=2;
							}
						}
						else {
							k=2;
						}
					}
					if(k==1) {
						System.out.println("중복된 아이디가 존재합니다.");
						continue;
					}
					else if(k==2) {
						System.out.println("3-20자 이내의 영소문자/숫자로 이루어진 문자열로 입력해주세요.");
						continue;
					}

					System.out.println("현재 아이디 "+member.id+"를 새로운 아이디 "+newid+"로 정말 바꾸시겠습니까?(1(예), 2(아니요)");
					selectnum = sc.next().charAt(0);
					if(selectnum == '1') {
						System.out.println("변경이 완료되었습니다.");
						member.id= newid;
						mypageMenu(member);
						return;
					}
					else if(selectnum == '2') {
						System.out.println("변경을 취소합니다.");
						mypageMenu(member);
						return;
					}
					else {
						System.out.println("잘못된 입력입니다.");
						mypageMenu(member);
						return;
					}

				}
			}
			else if(select.equals("이름")) {
				while(true) {
					System.out.print("현재 이름: "+ member.name+"새로운 이름을 입력하세요:");
					newname = br.readLine();
					if(newname.charAt(0)==' ') {
						System.out.println("잘못된 입력입니다. 선행 공백을 허용하지 않습니다.");
						continue;
					}
					int al = nameisAlpa(newname);
					if(al==1) {
						System.out.println("이름은 한글 입력만 허용합니다.");   
						continue;
					}else
						break;  
				}
				System.out.println("현재 이름 "+member.name+"를 새로운 이름 "+newname+"로 정말 바꾸시겠습니까?(1(예), 2(아니요)");
				selectnum = sc.next().charAt(0);
				if(selectnum == '1') {
					System.out.println("변경이 완료되었습니다.");
					member.name= newname;
					mypageMenu(member);
					return;
				}
				else if(selectnum == '2') {
					System.out.println("변경을 취소합니다.");
					mypageMenu(member);
					return;
				}
				else {
					System.out.println("잘못된 입력입니다.");
					mypageMenu(member);
					return;
				}
			}
			else if(select.equals("전화번호")) {
				while(true) {
					System.out.print("현재 전화번호 : "+ member.phoneNum+"전화번호를 입력하세요:");
					newphoneNum = br.readLine();
					if(newphoneNum.charAt(0)!='0'||newphoneNum.charAt(1)!='1'||newphoneNum.charAt(2)!='0') {
						System.out.println("맨 앞의 숫자 세 개는 010만 허용합니다.");
						continue;
					}
					if(newphoneNum.charAt(3)=='-') {
						String[] num = newphoneNum.split("-");
						newphoneNum = num[0]+num[1]+num[2];
					}else if(newphoneNum.charAt(3)=='.') {
						String[] num = new String[3];
						for (int i = 0; i < num.length; i++) {
							num[i] = "";
						}
						for (int i = 0; i < 3; i++) {
							num[0] += Character.toString(newphoneNum.charAt(i));
						}
						for (int i = 4; i < 8; i++) {
							num[1] += Character.toString(newphoneNum.charAt(i));
						}
						for (int i = 9; i < 13; i++) {
							num[2] += Character.toString(newphoneNum.charAt(i));
						}
						newphoneNum = num[0]+num[1]+num[2];
					}else if(newphoneNum.charAt(3)=='_') {
						String[] num = newphoneNum.split("_");
						newphoneNum = num[0]+num[1]+num[2];
					}
					break;
				}
				System.out.println("현재 전화번호 "+member.phoneNum+"를 새로운 전화번호 "+newphoneNum+"로 정말 바꾸시겠습니까?(1(예), 2(아니요)");
				selectnum = sc.next().charAt(0);
				if(selectnum == '1') {
					System.out.println("변경이 완료되었습니다.");
					member.phoneNum= newphoneNum;
					mypageMenu(member);
					return;
				}
				else if(selectnum == '2') {
					System.out.println("변경을 취소합니다.");
					mypageMenu(member);
					return;
				}
				else {
					System.out.println("잘못된 입력입니다.");
					mypageMenu(member);
					return;
				}


			}
			else if(select.equals("질문")) {
				while(true) {
					System.out.print("현재 질문 : ");
					System.out.println(qarr[Integer.parseInt(member.question)-1]);
					System.out.println("1. 나의 가장 친한 친구 이름은?");
					System.out.println("2. 내가 가장 존경하는 인물은?");
					System.out.println("3. 내가 졸업한 초등학교는?");
					newq = br.readLine();
					if(newq.equals("1")|| newq.equals("2")|| newq.equals("3")) {
						switch (newq){
						}
						break;
					}
					else{
						System.out.println("1,2,3 중에 선택하세요.");
						continue;
					}

				}
				System.out.println("현재 질문 "+qarr[Integer.parseInt(member.question)-1]+"를 새로운 질문 "+qarr[Integer.parseInt(newq)-1]+"로 정말 바꾸시겠습니까?(1(예), 2(아니요)");
				selectnum = sc.next().charAt(0);
				if(selectnum == '1') {
					System.out.println("변경이 완료되었습니다.");
					member.question= newq;
					mypageMenu(member);
					return;
				}
				else if(selectnum == '2') {
					System.out.println("변경을 취소합니다.");
					mypageMenu(member);
					return;
				}
				else {
					System.out.println("잘못된 입력입니다.");
					mypageMenu(member);
					return;
				}
			}
			else if(select.equals("답")) {
				while(true) {
					System.out.println("현재 답: "+member.answer);
					System.out.print("새로운 답을 입력하세요:");
					newa = br.readLine();
					break;
				}
				System.out.println("현재 답 "+member.answer+"를 새로운 답 "+newa+"로 정말 바꾸시겠습니까?(1(예), 2(아니요)");
				selectnum = sc.next().charAt(0);
				if(selectnum == '1') {
					System.out.println("변경이 완료되었습니다.");
					member.answer= newa;
					mypageMenu(member);
					return;
				}
				else if(selectnum == '2') {
					System.out.println("변경을 취소합니다.");
					mypageMenu(member);
					return;
				}
				else {
					System.out.println("잘못된 입력입니다.");
					mypageMenu(member);
					return;
				}
			}
			else if(select.equals("비밀번호")) {
				while(true) {
					System.out.print("비밀번호를 입력하세요:");
					newpwd = br.readLine();
					if(newpwd.length()>=5&&newpwd.length()<=15) {
						int val = pwdCheck(newpwd);
						if(val==1) {
							System.out.println("비밀번호는 영소문자/숫자/특수문자로 입력되어야 합니다.");
							continue;
						}
						break;
					}else {
						System.out.println("비밀번호는 5-15자 이내에 입력해야합니다.");
						continue;
					}
				}
				System.out.println("현재 비밀번호 "+member.pwd+"를 새로운 비밀번호 "+newpwd+"로 정말 바꾸시겠습니까?(1(예), 2(아니요)");
				selectnum = sc.next().charAt(0);
				if(selectnum == '1') {
					System.out.println("변경이 완료되었습니다.");
					member.pwd = newpwd;
					mypageMenu(member);
					return;
				}
				else if(selectnum == '2') {
					System.out.println("변경을 취소합니다.");
					mypageMenu(member);
					return;
				}
				else {
					System.out.println("잘못된 입력입니다.");
					mypageMenu(member);
					return;
				}
			}
			else {
				System.out.println("잘못된 입력입니다.");
				mypageMenu(member);
				return;
			}
		}
		else {
			System.out.println("비밀번호가 일치하지 않습니다. 마이페이지로 돌아갑니다.");
			mypageMenu(member);
			return;
		}
	}



	public static void buyTicket(Member member) throws Exception {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("==========정기권 구매==========");
			System.out.println("1. 기간제    2. 사용횟수제");
			int in = Integer.parseInt(br.readLine());
			switch(in) {
			case 1:
				ticketPeriod(member);
				return;
			case 2:
				ticketNumber(member);
				return;
			default:
				System.out.println("다시 입력해주세요.");
			}
		}


	}



	public static void ticketNumber(Member member) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("1. 3회권    2. 7회권    3. 10회권");
		System.out.println("중 원하는 정기권을 선택하세요");
		int in,p;
		in = Integer.parseInt(br.readLine());
		switch(in) {
		case 1:
			System.out.println("3회권 정기권을 선택하였습니다. 결제하시겠습니까?(네:1,아니오:2)");
			p = Integer.parseInt(br.readLine());
			if(p==1) {
				System.out.println("27000원입니다");
				member.addTicket(new Ticket(3, "num"));
				System.out.println("구매 완료");
				member.point = new Point(member.date,member.time,27000,2700);
				writePoint(new Point(member.date,member.time,27000,2700),member);
				member.ticketCnt++;
				break;
			}

		case 2:
			System.out.println("7회권 정기권을 선택하였습니다. 결제하시겠습니까?(네:1,아니오:2)");
			p = Integer.parseInt(br.readLine());
			if(p==1) {
				System.out.println("59500원입니다");
				member.addTicket(new Ticket(7, "num"));
				System.out.println("구매 완료");
				member.point = new Point(member.date,member.time,59500,5950);
				writePoint(new Point(member.date,member.time,59500,5950),member);
				member.ticketCnt++;
				break;
			}
		case 3:
			System.out.println("10회권 정기권을 선택하였습니다. 결제하시겠습니까?(네:1,아니오:2)");
			p = Integer.parseInt(br.readLine());
			if(p==1) {
				System.out.println("80000원입니다");
				member.addTicket(new Ticket(10, "num"));
				System.out.println("구매 완료");
				member.point = new Point(member.date,member.time,80000,8000);
				writePoint(new Point(member.date,member.time,80000,8000),member);
				member.ticketCnt++;
				break;
			}


		}
	}




	public static void writePoint(Point point,Member member) {
		// TODO Auto-generated method stub
		File file = new File("Point"+member.id+".txt");
		FileWriter writer = null;
		count++;
		try {
			// 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
			writer = new FileWriter(file, true);
			writer.write(point.date+" "+point.time+" "+point.money+" "+point.point+"\n");
			writer.flush();

		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(writer != null) writer.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}




	public static void ticketPeriod(Member member) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("오늘 날짜를 입력하세요.");
		String date = br.readLine();
		while(true) {

			date = checkDate(date);
			if(date!=null)
				break;
			System.out.println("오늘 날짜를 입력하세요.");
			date = br.readLine();
		}


		System.out.println("1. 3일권 기간제 정기권    2. 7일권 기간제 정기권    3. 15일권 기간제    4. 30일권 기간제 정기권");
		System.out.println("중 원하는 정기권을 선택하세요");
		int in,p;
		in = Integer.parseInt(br.readLine());
		switch(in) {
		case 1:
			System.out.println("3일권 기간제 정기권을 선택하였습니다. 결제하시겠습니까?(네:1,아니오:2)");
			p = Integer.parseInt(br.readLine());
			if(p==1) {
				System.out.println("27000원입니다");
				Ticket ticket = new Ticket(3, "day");
				ticket.setDate(date);
				member.addTicket(ticket);   
				System.out.println("구매 완료");
				member.point = new Point(date,member.time,27000,2700);
				writePoint(new Point(date,member.time,27000,2700),member);
				member.ticketCnt++;
				break;
			}
		case 2:
			System.out.println("7일권 기간제 정기권을 선택하였습니다. 결제하시겠습니까?(네:1,아니오:2)");
			p = Integer.parseInt(br.readLine());
			if(p==1) {
				System.out.println("59500원입니다");
				Ticket ticket = new Ticket(7, "day");
				ticket.setDate(date);
				member.addTicket(ticket);
				System.out.println("구매 완료");
				member.point = new Point(member.date,member.time,59500,5950);
				writePoint(new Point(member.date,member.time,59500,5950),member);
				member.ticketCnt++;
				break;
			}
		case 3:
			System.out.println("15일권 기간제 정기권을 선택하였습니다. 결제하시겠습니까?(네:1,아니오:2)");
			p = Integer.parseInt(br.readLine());
			if(p==1) {
				System.out.println("120000원입니다");
				Ticket ticket = new Ticket(15, "day");
				ticket.setDate(date);
				member.addTicket(ticket);
				System.out.println("구매 완료");
				member.point = new Point(member.date,member.time,120000,12000);
				writePoint(new Point(member.date,member.time,120000,12000),member);
				member.ticketCnt++;
				break;
			}
		case 4:
			System.out.println("30일권 기간제 정기권을 선택하였습니다. 결제하시겠습니까?(네:1,아니오:2)");
			p = Integer.parseInt(br.readLine());
			if(p==1) {
				System.out.println("210000원입니다");
				Ticket ticket = new Ticket(30, "day");
				ticket.setDate(date);
				member.addTicket(ticket);
				System.out.println("구매 완료");
				member.point = new Point(member.date,member.time,210000,21000);
				writePoint(new Point(member.date,member.time,210000,21000),member);
				member.ticketCnt++;
				break;
			}

		}
	}



	public static void reserve(Member member) throws Exception {
		// TODO Auto-generated method stub
		while(true) {

			System.out.println("==========버스 예매==========");
			System.out.print("오늘 날짜를 입력하세요:");
			nowDate = br.readLine();
			nowDate = checkDate(nowDate);
			member.date = nowDate;
			System.out.print("현재 시간을 입력하세요:");
			nowTime = br.readLine();
			checkTime();
			member.time = nowTime;
			String dep,arr,goDate;
			System.out.print("출발지를 입력하세요:");
			dep = br.readLine();
			System.out.print("도착지를 입력하세요:");
			arr = br.readLine();
			System.out.print("출발 날짜를 입력하세요:");
			goDate = br.readLine();
			if(dep.equals(arr)){
				System.out.println("출발지와 도착지가 일치할 수 없습니다.");
				continue;
			}
			if(checkIsBlank(dep)==1||checkIsBlank(arr)==1)
				continue;
			for (int i = 0; i < 5; i++) {
				if(dep.equals(depPoint[i])) {
					for (int j = 0; j < 5; j++) {
						if(arr.equals(arrPoint[j])) {
							System.out.println("==========버스==========");
							System.out.println("출발지:"+dep);
							System.out.println("도착지:"+arr);
							System.out.println("출발 날짜:"+goDate);
							System.out.print("출발 시간:");
							for (int k = 0; k < time.length; k++) {
								System.out.print(time[k]+" ");
							}
							System.out.println();
							System.out.print("출발 시간을 입력하세요:");
							String depTime = br.readLine();
							System.out.print("예매를 원하는 좌석수를 입력하세요:");
							int seat = Integer.parseInt(br.readLine());

							if(seat>4 || seat<=0) {
								System.out.println("한번에 1명이상 4명이하 좌석만 구매가능합니다.");
								continue;
							}
							if(member.ticketCnt>0) {
								Bus bookedBus = bookSeatTicket(nowDate,member,dep,arr,depTime,seat);
								if(bookedBus!=null) {
									member.b.add(bookedBus);
									return;
								}

							}else {
								Bus bookedBus = bookSeat(dep,arr,depTime,seat);
								if(bookedBus!=null) {
									member.b.add(bookedBus);
									return;
								}


							}

						}
					}
				}
			}
		}

	}








	private static Bus bookSeatTicket(String nowDate, Member member, String dep, String arr, String depTime, int seat) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < bus.size(); i++) {
			if(bus.get(i).startingPoint.equals(dep)&&bus.get(i).destination.equals(arr)) {
				if(bus.get(i).startTime.equals(depTime)) {

					if(bus.get(i).people+seat<=10) {
						showSeat(bus.get(i).seat);
						System.out.print("원하시는 좌석을 입력해주세요:(예:01)");
						String wantSeat = br.readLine();
						String[] temp = wantSeat.split(" ");
						if(seat==temp.length) {
							for (int j = 0; j < temp.length; j++) {
								System.out.println("결제하시겠습니까?");
								System.out.println("1. 네     2. 아니오");
								int in = Integer.parseInt(br.readLine());
								if(in==1) {
									if(member.ticket[member.ticketCnt-1].type.equals("day")) {
										if(!bus.get(i).seat[2*Character.getNumericValue(temp[j].charAt(0))+Character.getNumericValue(temp[j].charAt(1))]) {
											bus.get(i).seat[2*Character.getNumericValue(temp[j].charAt(0))+Character.getNumericValue(temp[j].charAt(1))] = true;
											System.out.println("기간제 정기권을 사용하여 예매 완료");
											member.ticket[member.ticketCnt-1].remainDays--;
											return bus.get(i);
										}


									}if(member.ticket[member.ticketCnt-1].type.equals("num")) {
										if(!bus.get(i).seat[2*Character.getNumericValue(temp[j].charAt(0))+Character.getNumericValue(temp[j].charAt(1))]) {
											bus.get(i).seat[2*Character.getNumericValue(temp[j].charAt(0))+Character.getNumericValue(temp[j].charAt(1))] = true;
											System.out.println("정기권 사용 가능 횟수: "+member.ticket[member.ticketCnt-1].remainNum);
											System.out.println("횟수제 정기권을 사용하여 예매 완료");
											member.ticket[member.ticketCnt-1].remainNum--;
											return bus.get(i);
										}

									}else {
										if(!bus.get(i).seat[2*Character.getNumericValue(temp[j].charAt(0))+Character.getNumericValue(temp[j].charAt(1))]) {
											bus.get(i).seat[2*Character.getNumericValue(temp[j].charAt(0))+Character.getNumericValue(temp[j].charAt(1))] = true;
											writePoint(new Point(nowDate,nowTime,10000,1000), member);
											System.out.println("예매 완료");
											return bus.get(i);
										}

									}
								}else {
									System.out.println("결제 취소");
									return null;
								}
							}

						}else {
							return null;
						}

					}else {
						System.out.println("버스 예매 가능 좌석이 부족합니다.");

					}
				}
			}
		}
		return null;
	}




	private static int checkIsBlank(String st) {
		// TODO Auto-generated method stub
		for (int i = 0; i < st.length(); i++) {
			if(st.charAt(i)==' ')
				return 1;
		}
		return 0;
	}




	public static Bus bookSeat(String dep, String arr, String depTime, int seat) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < bus.size(); i++) {
			if(bus.get(i).startingPoint.equals(dep)&&bus.get(i).destination.equals(arr)) {
				if(bus.get(i).startTime.equals(depTime)) {

					if(bus.get(i).people+seat<=10) {
						showSeat(bus.get(i).seat);
						System.out.print("원하시는 좌석을 입력해주세요:(예:01)");
						String wantSeat = br.readLine();
						String[] temp = wantSeat.split(" ");
						if(seat==temp.length) {
							for (int j = 0; j < temp.length; j++) {
								System.out.println("결제하시겠습니까?");
								System.out.println("1. 네     2. 아니오");
								int in = Integer.parseInt(br.readLine());
								if(in==1) {
									if(!bus.get(i).seat[2*Character.getNumericValue(temp[j].charAt(0))+Character.getNumericValue(temp[j].charAt(1))]) {
										bus.get(i).seat[2*Character.getNumericValue(temp[j].charAt(0))+Character.getNumericValue(temp[j].charAt(1))] = true;
										System.out.println("예매 완료");
										return bus.get(i);
									}

								}else {
									System.out.println("결제 취소");
									return null;
								}
							}

						}else {
							return null;
						}

					}else {
						System.out.println("버스 예매 가능 좌석이 부족합니다.");

					}
				}
			}
		}
		return null;
	}

	public static void checkTime() {
		while(true) {

			int check = 0;
			if(!(nowTime.length()<=6) && !(nowTime.length()>=1)) {
				System.out.println("1글자 이상 6글자 이하로 입력해주세요.");
				continue; 
			}
			if(!nowTime.contains(":")) {
				if(nowTime.contains("시")) {
					String[] s1 = nowTime.split("시");
					if(s1[1].contains("분")) {
						String[] s2 = s1[1].split("분");
						if(s1[0].length()==1) {
							if(Integer.parseInt(s1[0])<0 || Integer.parseInt(s1[0])>9) {
								continue;
							}
							String temp ="0";
							s1[0] = temp.concat(s1[0]);
							if(s2[0].length()==1&&Integer.parseInt(s2[0])>=0&&Integer.parseInt(s2[0])<=9){
								String temp1 ="0"; 
								s2[0] = temp1.concat(s2[0]);
								nowTime = s1[0].concat(s2[0]);
								System.out.println(nowTime);
								break;
							}
							else if(s2[0].length()==2) {
								if(Integer.parseInt(s2[0])>=0&&Integer.parseInt(s2[0])<=59) {
									nowTime = s1[0].concat(s2[0]);
									System.out.println(nowTime);
									break;
								}
								continue;
							}
							else {
								continue;
							}
						}
						else if(s1[0].length()==2) {
							if(Integer.parseInt(s1[0])<0 || Integer.parseInt(s1[0])>23) {
								continue;
							}
							if(s2[0].length()==1&&Integer.parseInt(s2[0])>=0&&Integer.parseInt(s2[0])<=9){
								String temp1 ="0"; 
								s2[0] = temp1.concat(s2[0]);
								nowTime = s1[0].concat(s2[0]);
								System.out.println(nowTime);
								break;
							}
							else if(s2[0].length()==2) {
								if(Integer.parseInt(s2[0])>=0&&Integer.parseInt(s2[0])<=59) {
									nowTime = s1[0].concat(s2[0]);
									System.out.println(nowTime);
									break;
								}
								continue;
							}
							else {
								continue;
							}

						}
						else if(s1[0].length()==0) {
							if(Integer.parseInt(s1[0])==0) {
								if(s2[0].length()==1&&Integer.parseInt(s2[0])>=0&&Integer.parseInt(s2[0])<=9){
									String temp1 ="0"; 
									s2[0] = temp1.concat(s2[0]);
									nowTime = s1[0].concat(s2[0]);
									System.out.println(nowTime);
									break;
								}
								else if(s2[0].length()==2) {
									if(Integer.parseInt(s2[0])>=0&&Integer.parseInt(s2[0])<=59) {
										nowTime = s1[0].concat(s2[0]);
										System.out.println(nowTime);
										break;
									}
									continue;
								}
								else {
									continue;
								}
							}
							continue;
						}
						continue;    
					}
					continue;
				}
			}
			for(int i =0;i<nowTime.length();i++) {
				if(nowTime.charAt(i) != ':') {
					if(!Character.isDigit(nowTime.charAt(i))) {
						check=1;
					}
				}
			}
			if(check==1) {
				continue;
			}
			if(nowTime.contains(":")) {
				String[] s = nowTime.split(":");


				if(s[0].length()==1) {
					if(Integer.parseInt(s[0])<0 || Integer.parseInt(s[0])>9) {
						continue;
					}
					String temp ="0";
					s[0] = temp.concat(s[0]);
					if(s[1].length()==1) {
						if(Integer.parseInt(s[1])<0 || Integer.parseInt(s[1])>9) {
							continue;
						}
						String temp2 ="0";
						s[1] = temp2.concat(s[1]);
						nowTime = s[0].concat(s[1]);
						System.out.println(nowTime);
						break;
					}
					else if(s[1].length()==2) {
						if(Integer.parseInt(s[1])<0 || Integer.parseInt(s[1])>59) {
							continue;
						}
						nowTime = s[0].concat(s[1]);
						System.out.println(nowTime);
						break;
					}
					else if(s[1].length()==0) {
						continue; 
					}
				}
				else if(s[0].length()==2) {
					if(Integer.parseInt(s[0])<0 || Integer.parseInt(s[0])>23) {
						continue;
					}
					if(s[1].length()==1) {
						if(Integer.parseInt(s[1])<0 || Integer.parseInt(s[1])>9) {
							continue;
						}
						String temp2 ="0";
						s[1] = temp2.concat(s[1]);
						nowTime = s[0].concat(s[1]);
						System.out.println(nowTime);
						break;
					}
					else if(s[1].length()==2) {
						if(Integer.parseInt(s[1])<0 || Integer.parseInt(s[1])>59) {
							continue;
						}
						nowTime = s[0].concat(s[1]);
						System.out.println(nowTime);
						break;
					}
					else if(s[1].length()==0) {
						continue; 
					}

				}
				else if(s[0].length()==0) {
					continue;
				}

			}
		}

	}



	public static void showSeat(boolean[] seat) {
		// TODO Auto-generated method stub
		System.out.println("\t"+"0    1");
		int cnt = 0;
		for (int i = 0; i < 9; i+=2) {
			System.out.print((cnt++)+"\t");
			for (int j = 0; j < 2; j++) {

				if(seat[i+j]) {
					System.out.print("●"+"    ");
				}else
					System.out.print("○"+"    ");
			}


			System.out.println();
		}
	}




	public static void joinMember() throws Exception {
		// TODO Auto-generated method stub

		System.out.println("==========회원가입 메뉴==========");
		String name, phoneNum, id, pwd, q, a, birth;

		while(true) {
			System.out.print("이름을 입력하세요:");
			name = br.readLine();
			if(name.charAt(0)==' ') {
				System.out.println("잘못된 입력입니다. 선행 공백을 허용하지 않습니다.");
				continue;
			}
			if(name.length()<=1||name.length()>5)
				System.out.println("이름은 2-5글자의 문자열 입력만 허용합니다.");
			int al = nameisAlpa(name);
			if(al==1) {
				System.out.println("이름은 한글 입력만 허용합니다.");   
				continue;
			}else
				break;

		}
		while(true) {
			System.out.print("생년월일을 입력하세요:");
			birth = br.readLine();
			birth = checkDate(birth);
			if(birth!=null)
				break;
		}
		while(true) {
			System.out.print("핸드폰 번호를 입력하세요:");
			phoneNum = br.readLine();
			if(phoneNum.charAt(0)!='0'||phoneNum.charAt(1)!='1'||phoneNum.charAt(2)!='0') {
				System.out.println("맨 앞의 숫자 세 개는 010만 허용합니다.");
				continue;
			}
			if(phoneNum.charAt(3)=='-') {
				String[] num = phoneNum.split("-");
				phoneNum = num[0]+num[1]+num[2];
			}else if(phoneNum.charAt(3)=='.') {
				String[] num = new String[3];
				for (int i = 0; i < num.length; i++) {
					num[i] = "";
				}
				for (int i = 0; i < 3; i++) {
					num[0] += Character.toString(phoneNum.charAt(i));
				}
				for (int i = 4; i < 8; i++) {
					num[1] += Character.toString(phoneNum.charAt(i));
				}
				for (int i = 9; i < 13; i++) {
					num[2] += Character.toString(phoneNum.charAt(i));
				}
				phoneNum = num[0]+num[1]+num[2];
			}else if(phoneNum.charAt(3)=='_') {
				String[] num = phoneNum.split("_");
				phoneNum = num[0]+num[1]+num[2];
			}
			break;
		}
		while(true) {
			System.out.print("아이디를 입력하세요: ");
			id = br.readLine();
			int k =0;
			for(int i=0; i<list.size();i++) {
				if(list.get(i).id.equals(id)) {
					k=1;
				}
			}
			for(int i=0; i<id.length();i++) {
				if(id.length()>2 && id.length()<21){
					if(Character.isDigit(id.charAt(i))||Character.isLowerCase(id.charAt(i))) {

					}
					else {
						k=2;
					}
				}
				else {
					k=2;
				}
			}
			if(k==1) {
				System.out.println("중복된 아이디가 존재합니다.");
				continue;
			}
			else if(k==2) {
				System.out.println("3-20자 이내의 영소문자/숫자로 이루어진 문자열로 입력해주세요.");
				continue;
			}
			break;
		}
		while(true) {
			System.out.print("비밀번호를 입력하세요:");
			pwd = br.readLine();
			if(pwd.length()>=5&&pwd.length()<=15) {
				int val = pwdCheck(pwd);
				if(val==1) {
					System.out.println("비밀번호는 영소문자/숫자/특수문자로 입력되어야 합니다.");
					continue;
				}
				break;
			}else {
				System.out.println("비밀번호는 5-15자 이내에 입력해야합니다.");
				continue;
			}
		}
		while(true) {
			System.out.println("아이디 및 비밀번호 분실 시 사용될 질문을 고르세요");
			System.out.println("1. 나의 가장 친한 친구 이름은?");
			System.out.println("2. 내가 가장 존경하는 인물은?");
			System.out.println("3. 내가 졸업한 초등학교는?");
			q = br.readLine();
			if(q.equals("1")|| q.equals("2")|| q.equals("3")) {
				break;
			}
		}
		while(true) {
			System.out.print("질문에 해당하는 답을 입력하세요:");
			a = br.readLine();
			break;
		}
		Member mem = new Member(name,birth,phoneNum,id,pwd,q,a);
		mem.point = new Point("","",0,0);
		list.add(mem);

	}

	public static String checkDate(String birth) {
		// TODO Auto-generated method stub
		int year = 0, month = 0, day = 0;

		boolean birthCheck = false, letterCheck = false;

		String a,b = "0";
		int num0, num1 = 0;

		String c,d = "0";
		int num2, num3 = 0;

		String e,f = "0";
		int num4, num5 = 0;

		String g,h = "0";
		int num6, num7 = 0;

		String i,j = "0";
		int num8, num9 = 0;


		while(true) {
			int z =0;

			if(birth.charAt(0)==' ') {
				System.out.println("잘못된 입력입니다. 선행 공백을 허용하지 않습니다.");
				return null;}

			// 011028

			if(birth.length() == 6) {

				for(int k = 0; k < birth.length(); k++) {
					if((int)birth.charAt(k) < 48 || (int)birth.charAt(k) > 57) {
						z = 1;
					}
				}

				if(z == 1) {
					continue;
				}

				a = Character.toString(birth.charAt(0));
				b = Character.toString(birth.charAt(1));
				c = Character.toString(birth.charAt(2));
				d = Character.toString(birth.charAt(3));
				e = Character.toString(birth.charAt(4));
				f = Character.toString(birth.charAt(5));

				num0 = Integer.parseInt(a);
				num1 = Integer.parseInt(b);
				num2 = Integer.parseInt(c);
				num3 = Integer.parseInt(d);
				num4 = Integer.parseInt(e);
				num5 = Integer.parseInt(f);

				year = 10 * num0 + num1;
				month = 10 * num2 + num3;
				day = 10 * num4 + num5;

				if(!(birth.contains("-")) && !(birth.contains("/")) &&
						!(birth.contains(".")) && !(birth.contains("_"))) {

					if(month >= 1 && month <= 12) {

						switch(month) {
						case 2:
							if(day >= 1 && day <= 28) {
								if(year >= 0 && year <= 21) {
									String n = "20";

									return n.concat(birth);

								}
								else {
									String n = "19";

									return n.concat(birth);
								}
							}
							break;
						case 4:
						case 6:
						case 9:
						case 11:
							if(day >= 1 && day <= 30) {
								if(year >= 0 && year <= 21) {
									String n = "20";
									return n.concat(birth);
								}
								else {
									String n = "19";
									return n.concat(birth);
								}
							}
							break;
						default:
							if(day >= 1 && day <= 31) {
								if(year >= 0 && year <= 21) {
									String n = "20";
									return n.concat(birth);
								}
								else {
									String n = "19";
									return n.concat(birth);
								}
							}
							break;
						}
					}

				}
				else {
					continue;
				}

			}

			// 20201028    01-10-28
			else if(birth.length() == 8) {

				a = Character.toString(birth.charAt(0));
				b = Character.toString(birth.charAt(1));
				c = Character.toString(birth.charAt(2));
				d = Character.toString(birth.charAt(3));
				e = Character.toString(birth.charAt(4));
				f = Character.toString(birth.charAt(5));
				g = Character.toString(birth.charAt(6));
				h = Character.toString(birth.charAt(7));

				// 20011028
				if(!(birth.contains("-")) && !(birth.contains("/")) &&
						!(birth.contains(".")) && !(birth.contains("_"))) {

					num0 = Integer.parseInt(a);
					num1 = Integer.parseInt(b);
					num2 = Integer.parseInt(c);
					num3 = Integer.parseInt(d);
					num4 = Integer.parseInt(e);
					num5 = Integer.parseInt(f);
					num6 = Integer.parseInt(g);
					num7 = Integer.parseInt(h);

					year = 1000 * num0 + 100 * num1 + 10 * num2 + num3;
					month = 10 * num4 + num5;
					day = 10 * num6 + num7;

					if(year >= 1921   && year <= 2021) {

						if(month >= 1 && month <= 12) {

							switch(month) {
							case 2:
								if(day >= 1 && day <= 28) {
									return birth;
								}
								break;
							case 4:
							case 6:
							case 9:
							case 11:
								if(day >= 1 && day <= 30) {
									return birth;
								}
								break;
							default:
								if(day >= 1 && day <= 31) {
									return birth;
								}
								break;
							}

						}
					}
				}

				// 01-10-28
				else if(birth.contains("-") || birth.contains("/") ||
						birth.contains(".") || birth.contains("_")) {



					num0 = Integer.parseInt(a);
					num1 = Integer.parseInt(b);
					//num2 = Integer.parseInt(c);
					num3 = Integer.parseInt(d);
					num4 = Integer.parseInt(e);
					//num5 = Integer.parseInt(f);
					num6 = Integer.parseInt(g);
					num7 = Integer.parseInt(h);

					year = 10 * num0 + num1;
					month = 10 * num3 + num4;
					day = 10 * num6 + num7;
					/*
                     System.out.println(year);
                     System.out.println(month);
                     System.out.println(day);*/

					// birthCheck 
					if(month >= 1 && month <= 12) {
						if(month == 2) {
							if(day >= 1 && day <= 28) {
								birthCheck = true;
							}
						}
						else if(month == 4 || month == 6 || month == 9 || month == 11) {
							if(day >= 1 && day <= 30) {
								birthCheck = true;
							}
						}
						else {
							if(day >= 1 && day <= 31) {
								birthCheck = true;
							}
						}
					}
					else {
						birthCheck = false;
					}
				}

				//System.out.println(birthCheck);

				// letterCheck
				if((birth.charAt(2)=='-') && (birth.charAt(5)=='-'))
					letterCheck = true;
				else if((birth.charAt(2)=='/') && (birth.charAt(5)=='/'))
					letterCheck = true;
				else if((birth.charAt(2)=='.') && (birth.charAt(5)=='.'))
					letterCheck = true;
				else if((birth.charAt(2)=='_') && (birth.charAt(5)=='_'))
					letterCheck = true;

				if(birthCheck && letterCheck) {
					if((birth.charAt(2))=='-') {
						String[] birthday = birth.split("-");
						birth = birthday[0] + birthday[1] + birthday[2];

						if(year >= 0 && year <= 21) {
							String n = "20";
							return  n.concat(birth);
						}
						else {
							String n = "19";
							return  n.concat(birth);
						}
					}
					else if((birth.charAt(2))=='/') {
						String[] birthday = birth.split("/");
						birth = birthday[0] + birthday[1] + birthday[2];

						if(year >= 0 && year <= 21) {
							String n = "20";
							return  n.concat(birth);
						}
						else {
							String n = "19";
							return  n.concat(birth);
						}
					}
					else if((birth.charAt(2))=='.') {
						String[] birthday = new String[3];
						for(int k = 0; k < birthday.length; k++) {
							birthday[k] = "";
						}
						for(int k = 0; k < 2; k++) {
							birthday[0] += Character.toString(birth.charAt(k));
						}
						for(int k = 3; k < 5; k++) {
							birthday[1] += Character.toString(birth.charAt(k));
						}
						for(int k = 6; k < 8; k++) {
							birthday[2] += Character.toString(birth.charAt(k));
						}
						birth = birthday[0] + birthday[1] + birthday[2];

						if(year >= 0 && year <= 21) {
							String n = "20";
							return  n.concat(birth);
						}
						else {
							String n = "19";
							return  n.concat(birth);
						}
					}
					else if((birth.charAt(2))=='_') {
						String[] birthday = birth.split("_");
						birth = birthday[0] + birthday[1] + birthday[2];

						if(year >= 0 && year <= 21) {
							String n = "20";
							return  n.concat(birth);
						}
						else {
							String n = "19";
							return  n.concat(birth);
						}
					}
				}

			}

			// 2021-03-29
			else if(birth.length() == 10) {
				if((birth.contains("-")) || (birth.contains("/")) ||
						(birth.contains(".")) || (birth.contains("_"))) {

					a = Character.toString(birth.charAt(0));
					b = Character.toString(birth.charAt(1));
					c = Character.toString(birth.charAt(2));
					d = Character.toString(birth.charAt(3));
					e = Character.toString(birth.charAt(4));
					f = Character.toString(birth.charAt(5));
					g = Character.toString(birth.charAt(6));
					h = Character.toString(birth.charAt(7));
					i = Character.toString(birth.charAt(8));
					j = Character.toString(birth.charAt(9));

					num0 = Integer.parseInt(a);
					num1 = Integer.parseInt(b);
					num2 = Integer.parseInt(c);
					num3 = Integer.parseInt(d);
					//num4 = Integer.parseInt(e);
					num5 = Integer.parseInt(f);
					num6 = Integer.parseInt(g);
					//num7 = Integer.parseInt(h);
					num8 = Integer.parseInt(i);
					num9 = Integer.parseInt(j);

					year = 1000 * num0 + 100 * num1 + 10 * num2 + num3;
					month = 10 * num5+ num6;
					day = 10 * num8 + num9;

					//System.out.println(year);
					//System.out.println(month);
					//System.out.println(day);

					// birthCheck
					if(month >= 1 && month <= 12) {
						if(month == 2) {
							if(day >= 1 && day <= 28) {
								birthCheck = true;
							}
						}
						else if(month == 4 || month == 6 || month == 9 || month == 11) {
							if(day >= 1 && day <= 30) {
								birthCheck = true;
							}
						}
						else {
							if(day >= 1 && day <= 31) {
								birthCheck = true;
							}
						}
					}
					else {
						birthCheck = false;
					}

					//System.out.println(birthCheck);

					// letterCheck
					if((birth.charAt(4)=='-') && (birth.charAt(7)=='-'))
						letterCheck = true;
					else if((birth.charAt(4)=='/') && (birth.charAt(7)=='/'))
						letterCheck = true;
					else if((birth.charAt(4)=='.') && (birth.charAt(7)=='.'))
						letterCheck = true;
					else if((birth.charAt(4)=='_') && (birth.charAt(7)=='_'))
						letterCheck = true;

					if(birthCheck && letterCheck) {
						if((birth.charAt(4))=='-') {
							String[] birthday = birth.split("-");
							birth = birthday[0] + birthday[1] + birthday[2];

							return birth;
						}
						else if((birth.charAt(4))=='/') {
							String[] birthday = birth.split("/");
							birth = birthday[0] + birthday[1] + birthday[2];
							return birth;
						}
						else if((birth.charAt(4))=='.') {
							String[] birthday = new String[3];
							for(int k = 0; k < birthday.length; k++) {
								birthday[k] = "";
							}
							for(int k = 0; k < 4; k++) {
								birthday[0] += Character.toString(birth.charAt(k));
							}
							for(int k = 5; k < 7; k++) {
								birthday[1] += Character.toString(birth.charAt(k));
							}
							for(int k = 8; k < 10; k++) {
								birthday[2] += Character.toString(birth.charAt(k));
							}
							birth = birthday[0] + birthday[1] + birthday[2];
							return birth;
						}
						else if((birth.charAt(4))=='_') {
							String[] birthday = birth.split("_");
							birth = birthday[0] + birthday[1] + birthday[2];
							return birth;
						}
					}

				}
			}

			else {
				System.out.println("날짜를 올바르게 입력하세요.");
				return null;
			}

		}
	}




	public static void showPoint(Member member) {
		if(count>0) {
			File file = new File("Point"+member.id+".txt");
			ArrayList<String> point = new ArrayList<>();
			try{
				if(checkBeforeFile(file)){
					BufferedReader br = new BufferedReader(new FileReader(file));

					String str = br.readLine();

					while(str != null){
						String[] st = str.split(" ");
						String s="";
						for (int i = 0; i < st.length; i++) {
							if(i!=2) {
								s+=st[i]+" ";
							}
						}
						point.add(s);
						str = br.readLine();

					}
					for (int i = 0; i < point.size(); i++) {
						System.out.println(point.get(i));
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}



	}


	public static boolean checkBeforeFile(File file){
		if(file.exists()){
			if(file.isFile() && file.canRead()){
				return true;
			}
		}
		return false;

	}

	public static int pwdCheck(String pwd) {
		// TODO Auto-generated method stub
		for (int i = 0; i < pwd.length(); i++) {
			boolean result = Character.toString(pwd.charAt(i)).matches("[0-9|a-z]*");
			if(!Character.isLowerCase(pwd.charAt(i))&&!Character.isDigit(pwd.charAt(i))&&!result) {
				return 1;
			}
		}
		return 0;
	}




	public static int nameisAlpa(String name) {
		// TODO Auto-generated method stub
		for (int i = 0; i < name.length(); i++) {
			if(name.charAt(i)>='a'&&name.charAt(i)<='z') {
				return 1;
			}
			if(name.charAt(i)>='A'&&name.charAt(i)<='Z') {
				return 1;
			}
		}
		return 0;
	}






}