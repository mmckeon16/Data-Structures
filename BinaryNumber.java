/**
 * @author Mary McKeon
 * CS 284
 * Section B
 *
 */
public class BinaryNumber {
	private int[] data;
	private boolean overflow = false;
	
	public BinaryNumber (int length){
		data = new int[length];
		for (int i = 0; i < length; i++){
			data[i] = 0;
		}
	}
	
	public BinaryNumber (String str){
		data = new int[str.length()];
		for (int i = 0; i<str.length(); i++){
			data[i] = Integer.parseInt(str.substring(i,i+1));
		}
	}
	
	/**
	 * 
	 * @return int length of the binary number
	 */
	public int getLength(){
		return this.data.length;
	}
	
	public int getDigit(int index){
		if (index >= 0 && index < data.length){
			return data[index];
		} else{
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * 
	 * @param amount 
	 */
	public void shiftR(int amount){
		if (amount == 0){
			return;
		}
		if (amount>0){
			int[] temp = data;
			data = new int[temp.length+amount];
			for (int i = 0; i < amount; i++){
				data[i] = 0;
			}
			int count = 0;
			for (int k = amount; k < temp.length + amount; k++){
				data[k] = temp[count];
				count++;
			}
		}
	}
	
	public void add(BinaryNumber aBinaryNum){
		if (this.getLength() != aBinaryNum.getLength()){
			System.out.println("These numbers are not equal");
			return;
		}
		int carry = 0;
		int[] temp = new int[this.getLength()];
		for (int i = 0; i < aBinaryNum.getLength(); i++){
			if (this.getDigit(i) + aBinaryNum.getDigit(i) + carry == 3){
				carry = 1;
				temp[i] = 1;
				continue;
			}
			if (this.getDigit(i) + aBinaryNum.getDigit(i) + carry == 2){
				carry = 1;
				temp[i] = 0;
				continue;
			}
			if (this.getDigit(i) + aBinaryNum.getDigit(i) + carry == 1){
				carry = 0;
				temp[i] = 1;
				continue;
			}
			if (this.getDigit(i) + aBinaryNum.getDigit(i) + carry == 0){
				carry = 0;
				temp[i] = 0;
				continue;
			}
			carry = 0;
		}
		if (carry == 1){
			this.overflow = true;
		}
		data = temp;
		carry = 0;
	}
	
	public void clearOverflow(){
		this.overflow = false;
	}
	
	public String toString(){
		if (this.overflow == true){
			return "Overflow";
		} else{
			String temp = "";
			for (int i = 0; i < this.getLength(); i++){
				temp += this.getDigit(i);
			}
			return temp;
		}
	}
	
	public static void main(String[] args){
		BinaryNumber num = new BinaryNumber("00111");
		BinaryNumber num2 = new BinaryNumber("11100");
		System.out.println(num.getLength());
		System.out.println(num.getDigit(0));
		//System.out.println(num.getIndex(4));
		//num.shiftR(4);
		//num.shiftR(0);
		//num.shiftR(7);
		
		num.add(num2);
		//System.out.println(num.overflow);
		System.out.println(num.toString());
	}
}
