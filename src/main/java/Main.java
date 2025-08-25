import br.com.mariojp.solid.srp.*;

public class Main {
	
	public static void main(String[] args) {
		
		Order order = new Order();
		order.add(new Item("Café", 8.0, 2)); // 16
		order.add(new Item("Bolo", 12.5, 1)); // 12.5 -> subtotal 28.5
		String receipt = new ReceiptService().generate(order);
		System.out.println(receipt);
		
	}
}
