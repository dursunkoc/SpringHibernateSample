import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aric.sample.domain.Campaign;
import com.aric.sample.domain.Offer;
import com.aric.sample.repository.CampaignResponseRepository;
import com.aric.sample.service.CampaignOfferDefinitionService;

/**
 * 
 */

/**
 * @author Dursun KOC
 * 
 */
public class Main {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/spring/app-context.xml");
		main2();
	}

	public static void main2() {
		final CampaignResponseRepository responseRepository = context
				.getBean(CampaignResponseRepository.class);

		Thread threads[] = new Thread[] { new Thread() {
			@Override
			public void run() {
				responseRepository.writeCampaignResponse(1L);
				responseRepository.writeCampaignResponse(2L);
				responseRepository.writeCampaignResponse(3L);
			}
		}, new Thread() {
			@Override
			public void run() {
				responseRepository.writeCampaignResponse(1L);
				responseRepository.writeCampaignResponse(2L);
				responseRepository.writeCampaignResponse(3L);
			}
		}, new Thread() {
			@Override
			public void run() {
				responseRepository.writeCampaignResponse(1L);
				responseRepository.writeCampaignResponse(2L);
				responseRepository.writeCampaignResponse(3L);
			}
		}, new Thread() {
			@Override
			public void run() {
				responseRepository.writeCampaignResponse(1L);
				responseRepository.writeCampaignResponse(2L);
				responseRepository.writeCampaignResponse(3L);
			}
		}, };

		for (Thread thread : threads) {
			thread.start();
		}
		System.out.println("All threads are fired!");

	}

	public static void main1() {
		CampaignOfferDefinitionService service = context
				.getBean(CampaignOfferDefinitionService.class);
		List<Campaign> campaigns = getHunkyMunky();
		service.persistCampaigns(campaigns);
	}

	private static List<Campaign> getHunkyMunky() {
		List<Campaign> campaigns = new ArrayList<Campaign>();
		campaigns.add(getHunky());
		campaigns.add(getStupid());
		campaigns.add(getMunky());
		return campaigns;
	}

	private static Campaign getStupid() {
		Campaign campaign = new Campaign();
		campaign.setCreateDate(new Date());
		campaign.setStartDate(new Date());
		campaign.setEndDate(new Date());
		campaign.setIsAbstract(0);
		campaign.setName("STUPID");
		return campaign;
	}

	private static Campaign getMunky() {
		Campaign campaign = new Campaign();
		campaign.setCreateDate(new Date());
		campaign.setStartDate(new Date());
		campaign.setEndDate(new Date());
		campaign.setIsAbstract(0);
		campaign.setName("Munky");
		return campaign;
	}

	private static Campaign getHunky() {
		Campaign campaign = new Campaign();
		campaign.setCreateDate(new Date());
		campaign.setStartDate(new Date());
		campaign.setEndDate(new Date());
		campaign.setIsAbstract(0);
		campaign.setName("Hunky");
		return campaign;
	}

	public static void main0() {
		CampaignOfferDefinitionService service = context
				.getBean(CampaignOfferDefinitionService.class);
		Campaign campaignWithOffers = service.getCampaignWithOffers(1L);
		System.out.println("Id: " + campaignWithOffers.getId());
		System.out.println("Name: " + campaignWithOffers.getName());
		System.out.println("Start Date: " + campaignWithOffers.getStartDate());
		System.out.println("End Date: " + campaignWithOffers.getEndDate());
		System.out.println("IsAbstract: " + campaignWithOffers.getIsAbstract());
		System.out.println("Date: " + campaignWithOffers.getCreateDate());
		int i = 0;
		for (Offer offer : campaignWithOffers.getOfferSet()) {
			i++;
			System.out.println("\tOffer -" + i + "- Id: " + offer.getId());
			System.out.println("\tOffer -" + i + "- Name: " + offer.getName());
			System.out.println("\tOffer -" + i + "- Start Date: "
					+ offer.getStartDate());
			System.out.println("\tOffer -" + i + "- End Date: "
					+ offer.getEndDate());
			System.out.println("\tOffer -" + i + "- Date: "
					+ offer.getCreateDate());
		}
		System.out.println("Done!");
	}
}
