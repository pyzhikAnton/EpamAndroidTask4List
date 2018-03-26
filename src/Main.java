
public class Main {

	public static void main(String[] args) {
		
		MyArrList<Integer> arr = new MyArrList<>();
		for (int i = 0;i<26;i++) {
			arr.add((int)(Math.random()*15+25));
		}
		System.out.println(arr+""+arr.size());
		arr.remove(5);
		System.out.println(arr+""+arr.size());
		//arr.removeAll(arr);
		//System.out.println(arr+""+arr.size());
		MyArrList<Integer> arr2 = (MyArrList<Integer>) arr.subList(3, 8);
		//System.out.println(arr+""+arr.size());
		System.out.println(arr2+""+arr2.size());
	}

}
