package servico;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import model.Venda;
import serializer.VendaSerializer;

public class GeradorVendas {
	
	private static Random rand  = new Random();
	private static long op = 0;
	private static BigDecimal valorIngresso = BigDecimal.valueOf(500);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VendaSerializer.class.getName());
		
		//foi criado o proprio serializador utilizando o jackson, pois por padrão é passado string e estou enviando uma venda
		try(KafkaProducer<String, Venda>  producer = new KafkaProducer<String, Venda>(properties)){
			while(true) {
				Venda venda = geraVenda();
				ProducerRecord<String, Venda> record = new ProducerRecord<String, Venda>("teste", venda);
				/*o metodo send é assincrono e não espera a execução
				para que essa chamada possa aguardar é necessário chamar o metodo get().
				... ainda assim não é possivel receber uma resposta do envio, logo é necessário utilizar um metodo sobrecarregado
				de send, que possibilita receber um callback
				*/
				producer.send(record, (data, e) -> {
					if(e != null) {
						e.printStackTrace();
						return;
					}
				}).get();
				
				//como a geração é rapida demais acrescento um sleep para que ele possa aguardar a cada disparo de venda
				Thread.sleep(200);
			}
		}
	}

	private static Venda  geraVenda() {
		long Cliente = rand.nextLong();
		int qtdIngresso = rand.nextInt(10);
		return new Venda(op++, Cliente, qtdIngresso, valorIngresso.multiply(BigDecimal.valueOf(qtdIngresso)));
	}
}
