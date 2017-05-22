
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Sms;

@Repository
public interface SmsRepository extends JpaRepository<Sms, Integer> {

	@Query("select s from Sms s where s.recipient.userAccount.id=?1")
	Collection<Sms> findMyReceivedMessages(int uaId);

	@Query("select s from Sms s where s.sender.userAccount.id=?1")
	Collection<Sms> findMySendMessages(int uaId);
}